package com.javacourse.mail;

import com.javacourse.security.command.SignInCommand;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Multithreading emails sender class.
 * Sends email to recipient
 */
public class MailThread extends Thread{

    private final static Logger logger;

    private String to;
    private String subject;
    private String message;
    private String user;
    private String password;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(MailThread.class);
    }

    public MailThread(String to, String subject, String message, String user, String password) {
        this.message = message;
        this.subject = subject;
        this.to = to;
        this.user = user;
        this.password = password;
    }

    @Override
    public void run() {
        Properties props = getMailProperties();
        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user,password);
            }
        });

        try
        {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(user));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    private Properties getMailProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return props;
    }
}
