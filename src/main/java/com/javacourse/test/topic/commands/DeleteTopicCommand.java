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
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import static com.javacourse.shared.WebPage.*;

public class DeleteTopicCommand implements Command {

    private final static Logger logger;
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private static final String ID = "id";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(DeleteTopicCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TopicService topicService = new TopicService();
        return getPageDependingOnWhetherDeleteIsSuccessful(request, topicService);
    }

    WebPage getPageDependingOnWhetherDeleteIsSuccessful(HttpServletRequest request, TopicService topicService){
        try {
            setTopicsAttribute(request);
            //if delete is successful, update topics request param and return topics page
            if(topicService.delete(request.getParameter(ID))){
                setTopicsAttribute(request);
                return new WebPage(WebPageBase.TOPICS_ADMIN_PAGE);
            }
        } catch (SQLException | UnsuccessfulQueryException | NumberFormatException e) {
            logger.error(e.getMessage());
            return new WebPage(WebPageBase.ERROR_ACTION);
        }
        //if delete was unsuccessful, set the error message and return to the same page
        setErrorMessage(request);
        return new WebPage(WebPageBase.TOPICS_ADMIN_PAGE);
    }

    private void setErrorMessage(HttpServletRequest request){
        String lang = Optional
                .ofNullable((String)request.getSession().getAttribute(LANG_PARAM))
                .orElse(ApplicationResources.getDefaultLang());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, new Locale(lang));
        request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.deletionUnsuccessful"));
    }

    private void setTopicsAttribute(HttpServletRequest request) throws UnsuccessfulQueryException, SQLException {
        final String TOPICS_ATTRIBUTE = "topics";
        List<Topic> topicsToShow = new TopicService().findAll();
        request.setAttribute(TOPICS_ATTRIBUTE, topicsToShow);
    }
}
