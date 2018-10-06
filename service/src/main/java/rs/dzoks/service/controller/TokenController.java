package rs.dzoks.service.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.dzoks.service.model.User;
import rs.dzoks.service.repository.UserRepository;
import rs.dzoks.service.util.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("token")
@RestController
public class TokenController extends CommonController {


    private final UserRepository userRepository;
    private final Notification mailSender;

    @Autowired
    public TokenController(UserRepository userRepository, Notification mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String generateToken(@RequestBody User user) throws UnauthorizedException {

        User dbUser=userRepository.getFirstByUsername(user.getUsername());
        if (dbUser==null || !BCrypt.checkpw(user.getPassword(),dbUser.getPassword())){
            throw new UnauthorizedException("Bad login!");
        }
        String token= UUID.randomUUID().toString().substring(0,6).toUpperCase();
        dbUser.setToken(token);
        dbUser.setLoggedIn((byte)0);
        dbUser.setTokenExpirationTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
        userRepository.saveAndFlush(dbUser);
        mailSender.sendToken(dbUser.getUsername(),dbUser.getToken(),dbUser.getTokenExpirationTime());
        return "Success";
    }
}
