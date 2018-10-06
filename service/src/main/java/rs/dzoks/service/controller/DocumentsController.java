package rs.dzoks.service.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.service.model.*;
import rs.dzoks.service.repository.*;
import rs.dzoks.service.soap.DocumentSOAP;
import rs.dzoks.service.soap.DocumentsListSOAP;
import rs.dzoks.service.util.DocumentConverter;
import rs.dzoks.service.util.BadRequestException;
import rs.dzoks.service.util.CommonController;
import rs.dzoks.service.util.ForbiddenException;
import rs.dzoks.service.util.UnauthorizedException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentsController extends CommonController {
    @Value("${document.driving}")
    private Integer drivingLicenceId;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final DrivingCategoryRepository drivingCategoryRepository;
    private final DocumentTypeRepository documentTypeRepository;

    private final DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository;

    @Autowired
    public DocumentsController(UserRepository userRepository, DocumentRepository documentRepository, DrivingCategoryRepository drivingCategoryRepository, DocumentTypeRepository documentTypeRepository, DocumentHasDrivingCategoryRepository documentHasDrivingCategoryRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.drivingCategoryRepository = drivingCategoryRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.documentHasDrivingCategoryRepository = documentHasDrivingCategoryRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public DocumentsListSOAP getDocuments(@RequestBody GetInfo info) throws UnauthorizedException, ForbiddenException, BadRequestException {
        DocumentsListSOAP list=new DocumentsListSOAP();
        List<DocumentType> documentTypeList=documentTypeRepository.findAll();
        List<DrivingCategory> drivingCategories=drivingCategoryRepository.findAll();
        User user=userRepository.getFirstByUsernameAndTokenAndTokenExpirationTimeAfter(info.getUsername(),info.getToken(), Timestamp.valueOf(LocalDateTime.now()));
        if (user==null || !BCrypt.checkpw(info.getPassword(),user.getPassword())){
            throw new UnauthorizedException("Bad login!");
        }
        if (user.getAdministrator().equals((byte)0) && !user.getJmbg().equals(info.getJmbg())){
            throw new ForbiddenException("Forbidden!");
        }
        User userToSearch=userRepository.getFirstByJmbg(info.getJmbg());
        if (userToSearch==null){
            throw new BadRequestException("Bad request");
        }
        List<Document> allDocuments=documentRepository.getAllByUserId(userToSearch.getId());
        for (Document document:allDocuments){

            List<DocumentHasDrivingCategory> cats=null;
            if (document.getDocumentTypeId().equals(drivingLicenceId)){
                cats=documentHasDrivingCategoryRepository.getAllByDocumentId(document.getId());
            }
            DocumentSOAP documentSOAP= DocumentConverter.convertDocument(document,userToSearch,cats,drivingCategories,documentTypeList);
            list.getDocuments().add(documentSOAP);
        }
        return list;
    }
}
