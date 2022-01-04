package ru.progwards.java2.lessons.builders;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class WorkerMail {
   String userAddress;
   String hostSmtp;
   Integer port;
   final String password;


   public WorkerMail(String userAddress, String hostSmtp, Integer port, String password){
      this.userAddress = userAddress;
      this.hostSmtp = hostSmtp;
      this.port = port;
      this.password = password;

   }

   public void sendMailTextAndFile(String text, String pathJar, String addressTo) throws MessagingException, IOException {
       Properties properties = new Properties();
       properties.put("mail.smtp.auth", true);
       properties.put("mail.smtp.host", hostSmtp);
       properties.put("mail.smtp.user", userAddress);
       properties.put("mail.smtp.port", port);
       properties.put("mail.smtp.ssl.enable", true);
       Session session = Session.getDefaultInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(getDefaultUserName(), password);
           }
       });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userAddress));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(addressTo));
        message.setSubject("проверка кода");
        message.setSentDate(new Date());
        final MimeBodyPart mailBody = new MimeBodyPart();
        final MimeBodyPart attachment = new MimeBodyPart();
        mailBody.setText(text);
        attachment.attachFile(pathJar);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mailBody);
        multipart.addBodyPart(attachment);

        message.setContent(multipart);
        Transport.send(message);
   }
}
