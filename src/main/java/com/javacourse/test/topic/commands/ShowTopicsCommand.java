package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowTopicsCommand implements Command {

    private final static Logger logger;
    private final static String TOPICS_ATTRIBUTE = "topics";
    private final static String ROLE_ATTRIBUTE = "role";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage page = WebPage.ERROR_PAGE;
        if(!setTopicsAttribute(request)){
            return page;
        }
        page = getTopicPageForClient(request);
        return page;
    }

    private boolean setTopicsAttribute(HttpServletRequest request){
        TopicService topicService = new TopicService();
        try {
            List<Topic> topics = topicService.findAll();
            request.setAttribute(TOPICS_ATTRIBUTE, topics);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private WebPage getTopicPageForClient(HttpServletRequest request){
        Role userRole = (Role) request.getSession().getAttribute(ROLE_ATTRIBUTE);
        if(userRole == Role.ADMIN)
            return WebPage.TOPICS_ADMIN_PAGE;
        return WebPage.TOPICS_USER_PAGE;
    }
}
