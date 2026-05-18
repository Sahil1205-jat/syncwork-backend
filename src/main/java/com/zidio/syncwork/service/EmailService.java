package com.zidio.syncwork.service;

import com.resend.Resend;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // This will read the API key from your environment variables
    @Value("${RESEND_API_KEY}")
    private String resendApiKey;

    public void sendEmail(String toEmail, String subject, String body) {
        // Initialize Resend with your API key
        Resend resend = new Resend(resendApiKey);

        // Build the email request
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                // IMPORTANT: Resend's free tier requires the "from" address to be 'onboarding@resend.dev'
                .from("SyncWork <onboarding@resend.dev>")
                .to(toEmail)
                .subject(subject)
                .html(body.replace("\n", "<br>")) // Convert newlines to HTML breaks
                .build();

        try {
            // Send the email
            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            System.out.println("✅ Email sent successfully via Resend! ID: " + data.getId());
        } catch (Exception e) {
            System.err.println("❌ Error sending email via Resend: " + e.getMessage());
        }
    }
}