package rs.dzoks.service.controller;

import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rs.dzoks.service.model.*;
import rs.dzoks.service.repository.*;
import rs.dzoks.service.soap.*;
import rs.dzoks.service.util.DocumentConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Endpoint
public class DocumentsEndpoint {

    @Value("${document.driving}")
    private Integer drivingLicenceId;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final DrivingCategoryRepository drivingCategoryRepository;
    private final DocumentTypeRepository documentTypeRepository;

    private final DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository;

    @Autowired
    public DocumentsEndpoint(UserRepository userRepository, DocumentRepository documentRepository, DrivingCategoryRepository drivingCategoryRepository, DocumentTypeRepository documentTypeRepository, DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.drivingCategoryRepository = drivingCategoryRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.documentHasDrivingCategoryRepository = documentHasDrivingCategoryRepository;
    }

    @PayloadRoot(namespace = "http://dzoks.rs/service/soap", localPart = "GetDocumentsRequest")
    @ResponsePayload
    public GetDocumentResponse processRequest(@RequestPayload GetDocumentsRequest request) {
        Gson gson=new Gson();
        DocumentsResponse response = new DocumentsResponse();
        GetDocumentResponse responseEnvelope=new GetDocumentResponse();
        response.setDocuments(new DocumentsListSOAP());
        List<DocumentType> documentTypeList=documentTypeRepository.findAll();
        List<DrivingCategory> drivingCategories=drivingCategoryRepository.findAll();
        User user=userRepository.getFirstByUsernameAndTokenAndTokenExpirationTimeAfter(request.getUsername(),request.getToken(), Timestamp.valueOf(LocalDateTime.now()));
        if (user==null || !BCrypt.checkpw(request.getPassword(),user.getPassword())){
            response.setError("401");
            responseEnvelope.setDocuments(gson.toJson(response,DocumentsResponse.class));
            return responseEnvelope;
        }
        List<Document> allDocuments=documentRepository.getAllByDateOfIssue(new Timestamp(request.getDate()));
        if (user.getAdministrator().equals((byte)0)){
            allDocuments.removeIf(d->!d.getUserId().equals(user.getId()));
        }
        for (Document document:allDocuments){
            User documentUser=userRepository.findById(document.getUserId()).orElse(null);
            if (documentUser==null){
                response.setError("400");
                responseEnvelope.setDocuments(gson.toJson(response,DocumentsResponse.class));
                return responseEnvelope;
            }
            List<DocumentHasDrivingCategory> cats=null;
            if (document.getDocumentTypeId().equals(drivingLicenceId)){
                cats=documentHasDrivingCategoryRepository.getAllByDocumentId(document.getId());
            }
            DocumentSOAP documentSOAP= DocumentConverter.convertDocument(document,documentUser,cats,drivingCategories,documentTypeList);
            response.getDocuments().getDocuments().add(documentSOAP);
        }
        responseEnvelope.setDocuments(gson.toJson(response,DocumentsResponse.class));
        return responseEnvelope;
    }
}
