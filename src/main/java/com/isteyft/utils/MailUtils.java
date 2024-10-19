package com.isteyft.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

    @Autowired
    private JavaMailSender mailSender; // 使用 JavaMailSender 接口
//    @Autowired
//    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}") // 使用双引号
    private String mailform;

    public void sendSimpleEmail(String mailto,String title,String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailform);
        message.setTo(mailto);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

}

