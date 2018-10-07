package rs.dzoks.admin_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.admin_application.model.User;
import rs.dzoks.admin_application.repository.UserRepository;
import rs.dzoks.admin_application.util.CommonController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController extends CommonController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll(){
        return repository.findAll();
    }
}
