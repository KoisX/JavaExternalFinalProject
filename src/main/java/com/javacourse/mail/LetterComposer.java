package com.javacourse.mail;

import com.javacourse.utils.ResourceBundleConfig;

import java.util.ResourceBundle;

public class LetterComposer {
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
