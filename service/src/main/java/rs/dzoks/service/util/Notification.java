package rs.dzoks.service.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class Notification {


    private final JavaMailSender mailSender;

    @Autowired
    public Notification(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Async
    public void sendToken(String email, String token,Timestamp tokenExpireTime) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("Sistem za upravljanje ličnim dokumentima");
        message.setFrom("etfbl.dzoks@gmail.com");
        message.setTo(email);
        String builder = "Dobili ste token za prijavljivanje na Sistem za upravljanje ličnim dokumentima" +
                "\n" +
                "Vaš token je: " + token +
                "\n" +
                "Token važi do: "+new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(tokenExpireTime);
        message.setText(builder);
        mailSender.send(message);
    }
}
