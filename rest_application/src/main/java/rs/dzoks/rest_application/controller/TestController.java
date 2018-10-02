package rs.dzoks.rest_application.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "Hello";
    }
}
