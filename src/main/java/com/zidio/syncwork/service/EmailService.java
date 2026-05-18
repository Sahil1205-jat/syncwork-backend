package com.zidio.syncwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // This 'from' address should match the username in application.properties
            message.setFrom("syncwork0@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Success: Email successfully sent to " + toEmail);

        } catch (Exception e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
            // Re-throwing the exception can help in debugging deployment logs
            throw new RuntimeException("Failed to send email", e);
        }
    }
}