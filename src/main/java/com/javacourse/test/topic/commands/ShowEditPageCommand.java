package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowEditPageCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = WebPage.TOPICS_ADMIN_FORWARD_EDIT;
        TopicService topicService = new TopicService();
        try {
            int topicId = Integer.parseInt(request.getParameter("id"));
            Topic topic = topicService.findById(topicId);
            request.setAttribute("topic", topic);
        } catch (SQLException | UnsuccessfulQueryException | NumberFormatException e) {
           webPage = WebPage.ERROR_FORWARD_ACTION;
        }
        return webPage;
    }
}
