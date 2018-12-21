package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicDAOMySql;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowTopicsCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TopicDAOMySql topicDAOMySql = new TopicDAOMySql(null);
        List<Topic> topics;
        try {
            topics = topicDAOMySql.findAll();
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }
        request.setAttribute("topics", topics);
        Role userRole = (Role) request.getSession().getAttribute("role");
        if(userRole == Role.ADMIN)
            return "/jsp/admin/topics.jsp";
        return "/jsp/user/topics.jsp";
    }
}
