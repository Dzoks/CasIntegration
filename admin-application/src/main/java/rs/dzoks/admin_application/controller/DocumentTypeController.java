package rs.dzoks.admin_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.admin_application.model.DocumentType;
import rs.dzoks.admin_application.repository.DocumentTypeRepository;
import rs.dzoks.admin_application.util.CommonController;

import java.util.List;

@RestController
@RequestMapping("document-type")
public class DocumentTypeController extends CommonController {

    private final DocumentTypeRepository repository;

    @Autowired
    public DocumentTypeController(DocumentTypeRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<DocumentType> getAll(){
        return repository.findAll();
    }
}
