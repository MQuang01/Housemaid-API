package com.cghue.projecthousemaidwebapp.service.impl;


import com.cghue.projecthousemaidwebapp.domain.Order;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(String toEmail, String subject, String body, String htmlContent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(sender); // Thay thế bằng email của bạn
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(subject);
        // Tạo một multipart
        Multipart multipart = new MimeMultipart();

        // Nội dung văn bản
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);


        // Nội dung HTML

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlContent, "text/html; charset=UTF-8");

        // Thêm các phần vào multipart
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(htmlPart);

        // Thiết lập multipart là nội dung của email
        message.setContent(multipart);

        javaMailSender.send(message);
    }

//        public void sendEmailWithTemplate(Order order, String emailOrder) throws MessagingException, IOException, WriterException {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", port);

//        Session session = Session.getInstance(props,
//                new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(email, password);
//                    }
//                });

//        MimeMessage message = new MimeMessage(session);
//        final MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//
//        try {
//            String imageResourceName = "anhQR";
//
//            messageHelper.setTo(emailOrder);
//            messageHelper.setFrom(new InternetAddress(email, "4SEASON CINEMAS", "UTF-8"));
//            messageHelper.setSubject("XÁC NHẬN ĐẶT VÉ");
//            messageHelper.setText(thymeleafService.getContent(order, imageResourceName), true);
//
//            ByteArrayOutputStream qrCodeOutputStream = new ByteArrayOutputStream();
//            createQRCode(qrCodeOutputStream, order.getOrderCode());
//            byte[] qrCodeBytes = qrCodeOutputStream.toByteArray();
//
//            final InputStreamSource imageSource = new ByteArrayResource(qrCodeBytes);
//            messageHelper.addInline(imageResourceName, imageSource, "image/png");
//
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException | WriterException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void sendEmailWithAttachment(String toEmail, String subject, String body, byte[] fileBytes, String fileName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("zcarebooking@gmail.com");
        helper.setTo(toEmail);
        helper.setText(body);
        helper.setSubject(subject);

        DataSource dataSource = new ByteArrayDataSource(fileBytes, "application/msword");
        helper.addAttachment(fileName, dataSource);

        javaMailSender.send(message);
    }

    public void sendEmailSimple(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
    }
}