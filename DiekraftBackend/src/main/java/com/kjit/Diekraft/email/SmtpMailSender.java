package com.kjit.Diekraft.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); //
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, true);
        javaMailSender.send(message);


    }

}
