package com.javacourse.test.topic.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteTopicCommand implements Command {

    private final static Logger logger;
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private final static String TOPICS_ATTRIBUTE = "topics";
    private static final String ID = "id";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.TOPICS_ADMIN_PAGE;
        TopicService topicService = new TopicService();
        try {
            if(topicService.delete(request.getParameter(ID))){
                if(!setTopicsAttribute(request)){
                    return WebPage.ERROR_ACTION;
                }
                return webPage;
            }
        } catch (SQLException | UnsuccessfulQueryException | NumberFormatException e) {
            logger.error(e.getMessage());
        }
        if(!setTopicsAttribute(request)){
            return WebPage.ERROR_ACTION;
        }
        setErrorMessage(request);
        return webPage;
    }

    private void setErrorMessage(HttpServletRequest request){
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale locale= new Locale(lang);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, locale);
        request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.deletionUnsuccessful"));
    }

    private boolean setTopicsAttribute(HttpServletRequest request){
        TopicService topicService = new TopicService();
        try {
            List<Topic> topicsToShow = topicService.findAll();
            request.setAttribute(TOPICS_ATTRIBUTE, topicsToShow);
        } catch (UnsuccessfulQueryException | SQLException e) {
            return false;
        }
        return true;
    }
}
