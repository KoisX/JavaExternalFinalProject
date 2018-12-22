package com.javacourse.test.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestDAOMySql;
import com.javacourse.test.TestService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowTestByTopicCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        TestService testService = new TestService();
        List<Test> tests;
        String topicId = request.getParameter("id");
        if(topicId==null)
            return WebPage.ERROR_ACTION;
        try{
            tests = testService.findByTopicId(topicId);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return WebPage.ERROR_ACTION;
        }
        request.setAttribute("tests", tests);
        Role userRole = (Role) request.getSession().getAttribute(WebKeys.getRoleKey());
        if(userRole == Role.ADMIN)
            return WebPage.TESTS_ADMIN_PAGE;
        return WebPage.TESTS_USER_PAGE;
    }
}
