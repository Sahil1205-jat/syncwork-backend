package com.zidio.syncwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * SyncWork ke notifications bhejne ke liye universal method
     * @param toEmail - Jisko mail bhejna hai (Employee/Admin)
     * @param subject - Mail ka heading
     * @param body - Mail ka message content
     */
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // Note: Ye 'From' address application.properties se match hona chahiye
            message.setFrom("noreply.syncwork@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Success: Email successfully sent to " + toEmail);

        } catch (Exception e) {
            System.err.println("❌ Error: Email send nahi ho paya! Check logs: " + e.getMessage());
            // Project ko crash hone se bachane ke liye hum sirf print kar rahe hain
        }
    }
}