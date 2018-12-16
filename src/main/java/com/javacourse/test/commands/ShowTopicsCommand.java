package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.command.SignInCommand;
import com.javacourse.shared.Command;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class ShowTopicsCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TopicDAO topicDAO = new TopicDAO();
        List<Topic> topics;
        try {
            topics = topicDAO.findAll();
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }
        request.setAttribute("topics", topics);
        return "/jsp/user/topics.jsp";
    }
}
