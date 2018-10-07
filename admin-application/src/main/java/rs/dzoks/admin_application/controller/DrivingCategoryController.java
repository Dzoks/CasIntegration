package rs.dzoks.admin_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.admin_application.model.DrivingCategory;
import rs.dzoks.admin_application.repository.DrivingCategoryRepository;
import rs.dzoks.admin_application.util.CommonController;

import java.util.List;

@RestController
@RequestMapping("driving-category")
public class DrivingCategoryController extends CommonController {

    @Autowired
    private DrivingCategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<DrivingCategory> getAll(){
        return repository.findAll();
    }
}
