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

    /**
     * Creates MailManager entity, initialized wih all necessary params
     * @param to email of recipient
     * @param subject subject of the letter
     * @param message message body
     * @param props Properties file with necessary params to initialize sender server
     * @return MailManager entity
     */
    public static MailManager createMailManager(String to, String subject, String message, Properties props){
        return new MailManager(to, subject, message, props);
    }

    /**
     * Sends letter to recipient's email
     */
    public void sendMail(){
        new MailThread(to, subject, message, user, password).start();
    }
}
