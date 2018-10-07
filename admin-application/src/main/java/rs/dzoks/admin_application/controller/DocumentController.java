package rs.dzoks.admin_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.admin_application.model.Document;
import rs.dzoks.admin_application.model.modelCustom.DocumentExtended;
import rs.dzoks.admin_application.repository.DocumentHasDrivingCategoryRepository;
import rs.dzoks.admin_application.repository.DocumentRepository;
import rs.dzoks.admin_application.util.CommonController;


@RequestMapping("document")
@RestController
public class DocumentController extends CommonController {


    private final DocumentRepository repository;
    private final DocumentHasDrivingCategoryRepository drivingCategoryRepository;

    @Autowired
    public DocumentController(DocumentRepository repository, DocumentHasDrivingCategoryRepository drivingCategoryRepository) {
        this.repository = repository;
        this.drivingCategoryRepository = drivingCategoryRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Document insert(@RequestBody DocumentExtended doc){
        Document document= repository.saveAndFlush(doc.getDocument());
        if (doc.getCategories()!=null){
            doc.getCategories().forEach(drivingCategory -> {
                drivingCategory.setDocumentId(document.getId());
            });
        }
        drivingCategoryRepository.saveAll(doc.getCategories());
        return document;
    }
}
