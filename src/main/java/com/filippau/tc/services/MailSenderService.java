package com.filippau.tc.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    public final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String username;

    public void send(String emailTo, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendFeed( String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(username);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);
    }
}




