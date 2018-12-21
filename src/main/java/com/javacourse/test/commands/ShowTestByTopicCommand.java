package com.javacourse.test.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.test.Test;
import com.javacourse.test.TestDAOMySql;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowTestByTopicCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TestDAOMySql testDAOMySql = new TestDAOMySql(null);
        List<Test> tests;
        String topicId = request.getParameter("id");
        if(topicId==null)
            return ApplicationResources.getErrorAction();
        try{
            tests = testDAOMySql.findByTopicId(topicId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return ApplicationResources.getErrorAction();
        }
        request.setAttribute("tests", tests);
        Role userRole = (Role) request.getSession().getAttribute(WebKeys.getRoleKey());
        if(userRole == Role.ADMIN)
            return "/jsp/admin/tests.jsp";
        return "/jsp/user/tests.jsp";
    }
}
