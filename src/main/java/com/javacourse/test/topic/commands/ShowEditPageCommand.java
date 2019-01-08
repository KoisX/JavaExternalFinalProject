package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import static com.javacourse.shared.WebPage.*;

public class ShowEditPageCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPageBase.TOPICS_ADMIN_EDIT);
        TopicService topicService = new TopicService();
        try {
            String topicId = request.getParameter("id");
            Topic topic = topicService.findById(topicId);
            request.setAttribute("topic", topic);
        } catch ( UnsuccessfulQueryException | NumberFormatException e) {
           webPage = new WebPage(WebPageBase.ERROR_ACTION);
        }
        return webPage;
    }
}
