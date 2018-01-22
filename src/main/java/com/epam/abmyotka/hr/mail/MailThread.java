package com.epam.abmyotka.hr.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread{
    private final static Logger LOGGER = LogManager.getLogger(MailThread.class);

    private String sendToEmail;
    private String fromEmail;
    private String mailTheme;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToEmail, String fromEmail, String mailTheme, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.fromEmail = fromEmail;
        this.mailTheme = mailTheme;
        this.mailText = mailText;
        this.properties = properties;
    }

    @Override
    public void run() {
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailTheme);
            message.setText(mailText);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Incorrect address: " + sendToEmail + "! Detail: " + e.getMessage());
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating message! Detail: " + e.getMessage());
        }
        try {
            Transport transport = mailSession.getTransport("smtps");
            transport.connect ("smtp.gmail.com", 465, "HR System", "");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            LOGGER.log(Level.INFO, "Email to " + sendToEmail + " from " + fromEmail + " sent successfully.");
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error sending message to " + sendToEmail + " from " + fromEmail + "! " +
                    "Detail: " + e.getMessage());
        }
    }
}
