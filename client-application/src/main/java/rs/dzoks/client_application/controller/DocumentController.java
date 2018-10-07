package rs.dzoks.client_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.client_application.model.*;
import rs.dzoks.client_application.repository.*;
import rs.dzoks.client_application.soap.DocumentSOAP;
import rs.dzoks.client_application.soap.DocumentsListSOAP;
import rs.dzoks.client_application.util.BadRequestException;
import rs.dzoks.client_application.util.CommonController;
import rs.dzoks.client_application.util.DocumentConverter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController extends CommonController {

    @Value("${document.driving}")
    private Integer drivingLicenceId;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final DrivingCategoryRepository drivingCategoryRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository;


    @Autowired
    public DocumentController(UserRepository userRepository, DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository, DocumentRepository documentRepository, DrivingCategoryRepository drivingCategoryRepository, DocumentTypeRepository documentTypeRepository) {
        this.userRepository = userRepository;
        this.documentHasDrivingCategoryRepository = documentHasDrivingCategoryRepository;
        this.documentRepository = documentRepository;
        this.drivingCategoryRepository = drivingCategoryRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public DocumentsListSOAP getDocuments(HttpServletRequest request) throws BadRequestException {
        String username=request.getRemoteUser();
        DocumentsListSOAP response=new DocumentsListSOAP();
        List<DocumentType> documentTypeList=documentTypeRepository.findAll();
        List<DrivingCategory> drivingCategories=drivingCategoryRepository.findAll();
        User currentUser=userRepository.getFirstByUsername(username);
        List<Document> allDocuments=documentRepository.findAll();
        if (currentUser.getAdministrator().equals((byte)0)){
            allDocuments.removeIf(d->!d.getUserId().equals(currentUser.getId()));
        }
        for (Document document:allDocuments){
            User documentUser=userRepository.findById(document.getUserId()).orElse(null);
            if (documentUser==null){
                throw new BadRequestException("Bad request!");
            }
            List<DocumentHasDrivingCategory> cats=null;
            if (document.getDocumentTypeId().equals(drivingLicenceId)){
                cats=documentHasDrivingCategoryRepository.getAllByDocumentId(document.getId());

            }
            DocumentSOAP documentSOAP= DocumentConverter.convertDocument(document,documentUser,cats,drivingCategories,documentTypeList);
            response.getDocuments().add(documentSOAP);
        }
        return response;
    }

}
