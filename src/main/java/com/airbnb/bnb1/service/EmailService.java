package com.airbnb.bnb1.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) throws MessagingException, MessagingException {
        // Create a MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        // Pass 'true' to the constructor to enable multipart support for attachments
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set email parameters
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);
        helper.setFrom("prasadrohit532@gmail.com");

        // Attach a file from the local file system
      //  FileSystemResource file = new FileSystemResource(new File(attachmentPath));
        FileSystemResource file = new FileSystemResource(attachmentPath);

        helper.addAttachment(file.getFilename(), file);

        // Send the email
        mailSender.send(message);
        System.out.println("Email sent successfully with attachment.");
    }
}
