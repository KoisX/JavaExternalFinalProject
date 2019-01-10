package com.javacourse.mail;

import java.util.Properties;

/**
 * Utility class which encapsulates email sending logic
 */
public class MailManager {

    private String password;
    private String user;
    private String to;
    private String subject;
    private String message;

    private MailManager() {
    }

    private MailManager(String to, String subject, String message, Properties props) {
        this.message = message;
        this.subject = subject;
        this.to = to;
        this.user = props.getProperty("mail.user.name");
        this.password = props.getProperty("mail.user.password");
    }

    public static MailManager createMailManager(String to, String subject, String message, Properties props){
        return new MailManager(to, subject, message, props);
    }

    public void sendMail(){
        new MailThread(to, subject, message, user, password).start();
    }
}
