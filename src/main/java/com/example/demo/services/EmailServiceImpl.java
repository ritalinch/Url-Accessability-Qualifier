package com.example.demo.services;

import com.example.demo.dto.UrlDto;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final ApplicationContext applicationContext;

    public EmailServiceImpl(JavaMailSender emailSender, ApplicationContext applicationContext) {
        this.emailSender = emailSender;
        this.applicationContext = applicationContext;
    }

    public void sendMessage(UrlDto url, String email) {
        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);
        assert message.getText() != null;

        message.setText(message.getText() + url.getUrl());
        message.setTo(email);

        emailSender.send(message);
    }
}