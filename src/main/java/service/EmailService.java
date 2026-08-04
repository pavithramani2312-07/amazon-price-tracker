package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import util.ConfigReader;

import java.io.File;
import java.util.Properties;

public class EmailService {
    public void sendEmail(String subject, String body, String attachmentpath){
       System.out.println(subject);
       System.out.println(body);
       System.out.println(attachmentpath);
       String sendermail = ConfigReader.getProperty("sender_email");
       String password = ConfigReader.getProperty("sender.password");
       String receivermail = ConfigReader.getProperty("receiver_email");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(
                props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return  new PasswordAuthentication(sendermail, password);
                    }
                });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendermail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivermail));
            message.setSubject(subject);
            MimeBodyPart textPart =
                    new MimeBodyPart();

            textPart.setText(body);

            MimeBodyPart attachmentPart =
                    new MimeBodyPart();

            attachmentPart.attachFile(
                    new File(attachmentpath));

            Multipart multipart =
                    new MimeMultipart();

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println(
                    "Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Email sent successfully");
    }
}