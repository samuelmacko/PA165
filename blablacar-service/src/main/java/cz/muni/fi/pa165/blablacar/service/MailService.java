package cz.muni.fi.pa165.blablacar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    private static final String EMAIL_SENDER = "blablacar@blabla.cz";
    private static final String HOST = "localhost";
    private static final String DEBUG_RECIPIENT = "m.geletka@gmail.com";
    private static final boolean ENABLE_EMAILS = false;

    private final Logger log = LoggerFactory.getLogger(getClass());


    public void sendEmail(String recipent, String subject, String content) {
        if (!ENABLE_EMAILS) {
            return;
        }
        if (DEBUG_RECIPIENT != null) {
            recipent = DEBUG_RECIPIENT;
        }
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", HOST);

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_SENDER));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);
        } catch (MessagingException ex) {
            log.warn("Can not send email to: " + recipent);
        }
    }

}
