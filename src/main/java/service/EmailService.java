package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import util.ConfigReader;

import java.io.File;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    public void sendEmail(String subject, String body, String reportpath, String attachmentpath, String extentReportFile){

        // Fallback for local execution
        final String sendermail =
                System.getenv("SENDER_EMAIL") != null
                        ? System.getenv("SENDER_EMAIL")
                        : ConfigReader.getProperty("sender_email");

        final String password =
                System.getenv("SENDER_PASSWORD") != null
                        ? System.getenv("SENDER_PASSWORD")
                        : ConfigReader.getProperty("sender.password");

        final String receivermail =
                System.getenv("RECEIVER_EMAIL") != null
                        ? System.getenv("RECEIVER_EMAIL")
                        : ConfigReader.getProperty("receiver_email");

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

            MimeBodyPart reportPart =
                    new MimeBodyPart();

            reportPart.attachFile(
                    new File(reportpath));

            MimeBodyPart dashboardPart =
                    new MimeBodyPart();

            dashboardPart.attachFile(
                    new File(attachmentpath));

            Multipart multipart =
                    new MimeMultipart();

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(reportPart);
            multipart.addBodyPart(dashboardPart);
            MimeBodyPart extentAttachment = new MimeBodyPart();
            extentAttachment.attachFile(extentReportFile);
            multipart.addBodyPart(extentAttachment);

            message.setContent(multipart);

            Transport.send(message);

            log.info(
                    "Email sent successfully!");


        } catch (Exception e) {
            log.error("Failed to create dashboard", e);
        }}
}