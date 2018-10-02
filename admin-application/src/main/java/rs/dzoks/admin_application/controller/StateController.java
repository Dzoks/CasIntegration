package rs.dzoks.admin_application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.admin_application.model.User;
import rs.dzoks.admin_application.repository.UserRepository;
import rs.dzoks.admin_application.util.BadRequestException;
import rs.dzoks.admin_application.util.CommonController;
import rs.dzoks.admin_application.util.ForbiddenException;
import rs.dzoks.admin_application.util.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("state")
public class StateController extends CommonController {

    private final UserRepository repository;

    @Autowired
    public StateController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User getUsername(HttpServletRequest request) throws ForbiddenException, BadRequestException, UnauthorizedException {
        User user=repository.getFirstByUsername(request.getRemoteUser());
        if (user!=null){
            if (user.getAdministrator().equals((byte)0)){
                throw new UnauthorizedException("Not gut inaf");
            }
            if (user.getToken() !=null && user.getTokenExpirationTime().after(Timestamp.valueOf(LocalDateTime.now())) && user.getLoggedIn().equals(((byte)1))){

                return user;
            }
            throw new ForbiddenException("Must enter token!");

        }
        throw new BadRequestException("User not found!");
    }

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public User checkToken(User user,HttpServletRequest request) throws BadRequestException, ForbiddenException, UnauthorizedException {
        User foundUser=repository.getFirstByUsername(request.getRemoteUser());
        if (foundUser!=null) {
            if (foundUser.getAdministrator().equals((byte)0)){
                throw new UnauthorizedException("Not gut inaf");
            }
            if (foundUser.getToken() != null && foundUser.getTokenExpirationTime().after(Timestamp.valueOf(LocalDateTime.now())) && user.getToken().equals(foundUser.getToken())) {
                foundUser.setLoggedIn((byte) 1);

                repository.saveAndFlush(foundUser);

                return foundUser;
            }
            throw new ForbiddenException("Token not valid!");
        }
        throw new ForbiddenException("User not found!");
    }
}
