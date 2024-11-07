//package com.airbnb.bnb1.controller;
//
//import com.airbnb.bnb1.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.mail.MessagingException;
//
//import java.io.File;
//
//@RestController
//@RequestMapping("/api/v1/email")  // replace with your desired endpoint path in your application.properties file. For example, /api/v1/email/send-confirmation-email.
//public class EmailController {
//
//
//    private EmailService emailService;
//
//    public EmailController(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @GetMapping("/sendEmail")
//    public String sendEmail(
//            @RequestParam String toEmail,
//            @RequestParam String subject,
//            @RequestParam String body,
//            @RequestParam File attachmentPath
//    ) {
//        try {
//            emailService.sendEmailWithAttachment(toEmail, subject, body, attachmentPath);
//            return "Email sent successfully!";
//        } catch (MessagingException e) {
//            return "Failed to send email: " + e.getMessage();
//        }
//    }
//}
//
//
//
//
//
//
////import jakarta.mail.internet.MimeMessage;
////import org.springframework.mail.SimpleMailMessage;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.mail.javamail.MimeMessageHelper;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import java.io.File;
////import java.nio.charset.StandardCharsets;
////import java.util.Objects;
////
////@RestController
////public class EmailController {
////
////    private final JavaMailSender mailSender;
////
////    public EmailController(JavaMailSender mailSender) {
////        this.mailSender = mailSender;
////    }
////
////    @RequestMapping("/send-email")
////    public String sendEmail() {
////        try {
////            SimpleMailMessage message = new SimpleMailMessage();
////
////            message.setFrom("prasadrohit532@gmail.com");
////            message.setTo("kraj70527@gmail.com");
////            message.setSubject("Simple test email from GC!");
////            message.setText("This is a sample email body for my first email!");
////
////            mailSender.send(message);
////            return "success!";
////        } catch (Exception e) {
////            return e.getMessage();
////        }
////    }
////
////    @RequestMapping("/send-email-with-attachment")
////    public String sendEmailWithAttachment() {
////        try {
////            MimeMessage message = mailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////
////            helper.setFrom("prasadrohit532@gmail.com");
////            helper.setTo("kraj70527@gmail.com");
////            helper.setSubject("Java email with attachment | From GC");
////            helper.setText("Please find the attached documents below");
////
////            helper.addAttachment("3_booking_confirmation.pdf", new File("C:\\bnb_bookings\\3_booking_confirmation.pdf"));
////           // helper.addAttachment("presentation.pptx", new File("C:\\Users\\Genuine Coder\\Documents\\Attachments\\presentation.pptx"));
////
////            mailSender.send(message);
////            return "success2!";
////        } catch (Exception e) {
////            return e.getMessage();
////        }
////    }
////
////    @RequestMapping("/send-html-email")
////    public String sendHtmlEmail() {
////        try {
////            MimeMessage message = mailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////
////            helper.setFrom("prasadrohit532@gmail.com");
////            helper.setTo("tutorial.genuinecoder@gmail.com");
////            helper.setSubject("Java email with attachment | From GC");
////
////            try (var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/email-content.html"))) {
////                helper.setText(
////                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
////                        true
////                );
////            }
////            helper.addInline("logo.png", new File("C:\\Users\\Genuine Coder\\Documents\\Attachments\\logo.png"));
////            mailSender.send(message);
////            return "success!";
////        } catch (Exception e) {
////            return e.getMessage();
////        }
////    }
////}