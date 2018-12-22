package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicDAOMySql;
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

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        TopicService topicService = new TopicService();
        List<Topic> topics;
        try {
            topics = topicService.findAll();
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return WebPage.ERROR_ACTION;
        }
        request.setAttribute("topics", topics);
        Role userRole = (Role) request.getSession().getAttribute("role");
        if(userRole == Role.ADMIN)
            return WebPage.TOPICS_ADMIN_PAGE;
        return WebPage.TESTS_USER_PAGE;
    }
}
