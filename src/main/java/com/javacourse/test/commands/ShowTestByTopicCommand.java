package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowTestByTopicCommand implements Command {

    private final static Logger logger;
    private final static String TESTS_ATTRIBUTE = "tests";
    private final static String ROLE_ATTRIBUTE = "role";
    private final static String ID_PARAM = "id";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.ERROR_ACTION;
        if(!setTestsAttribute(request)){
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
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private WebPage getTestPageForClient(HttpServletRequest request){
        Role userRole = (Role) request.getSession().getAttribute(ROLE_ATTRIBUTE);
        if(userRole == Role.ADMIN)
            return WebPage.TESTS_ADMIN_PAGE;
        return WebPage.TESTS_USER_PAGE;
    }
}
