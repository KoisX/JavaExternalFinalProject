package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;
import com.javacourse.test.topic.commands.ShowTopicsCommand;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import static com.javacourse.shared.WebPage.*;

public class ShowTestByTopicCommand implements Command {

    private final static Logger logger;
    private final static String TESTS_ATTRIBUTE = "tests";
    private final static String ROLE_ATTRIBUTE = "role";
    private final static String ID_PARAM = "id";
    private final static String TOPIC_ID = "topicId";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPageBase.ERROR_ACTION);
        if(!setTestsAttribute(request) || topicDoesNotExist(request)){
            return webPage;
        }
        webPage = getTestPageForClient(request);
        return webPage;
    }

    private boolean setTestsAttribute(HttpServletRequest request){
        TestService testService = new TestService();
        String topicId = request.getParameter(ID_PARAM);
        if(topicId==null)
            return false;
        try{
            List<Test> tests = testService.findByTopicId(topicId);
            request.setAttribute(TESTS_ATTRIBUTE, tests);
            request.setAttribute(TOPIC_ID, topicId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean topicDoesNotExist(HttpServletRequest request){
        TopicService topicService = new TopicService();
        String topicId = request.getParameter(ID_PARAM);
        boolean doesExist = false;
        if(topicId==null)
            return false;
        try{
            doesExist = topicService.doesTopicExist(topicId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
        return !doesExist;
    }

    private WebPage getTestPageForClient(HttpServletRequest request){
        Role userRole = (Role) request.getSession().getAttribute(ROLE_ATTRIBUTE);
        if(userRole == Role.ADMIN)
            return new WebPage(WebPageBase.TESTS_ADMIN_PAGE);
        return new WebPage(WebPageBase.TESTS_USER_PAGE);
    }
}
