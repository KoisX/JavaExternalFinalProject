package com.javacourse.mail;

import com.javacourse.utils.ResourceBundleConfig;

import java.util.ResourceBundle;

/**
 * Helper class for creating exam result letter body
 */
public class LetterComposer {
    /**
     * Creates exam result letter body
     * @param score exam score
     * @param maxScore maximal exam score
     * @param lang message body language
     * @return letter body
     */
    public static String compose(int score, int maxScore, String lang){
        ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang, "email_message");
        StringBuilder sb = new StringBuilder();
        sb.append(resourceBundle.getString("msg.hello"));
        sb.append(" ");
        sb.append(resourceBundle.getString("msg.youGot"));
        sb.append(" ");
        sb.append(score);
        sb.append(" ");
        sb.append(resourceBundle.getString("msg.outOf"));
        sb.append(" ");
        sb.append(maxScore);
        sb.append(" ");
        sb.append(resourceBundle.getString("msg.points"));
        sb.append("\n");
        sb.append(resourceBundle.getString("msg.farewell"));
        return sb.toString();
    }
}
