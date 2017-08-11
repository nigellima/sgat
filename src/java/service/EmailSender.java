/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author HUUFMA
 */
public class EmailSender {
    public EmailSender(){}
    
    public void enviar(String mailTo){
        Properties props = new Properties();
        props.put("mail.debug", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.mime.charset", "ISO-8859-1");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("nutis@huufma.br","coreme2008");
                        }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nutis@huufma.br"));
            message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(mailTo));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                            "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
