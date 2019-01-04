package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowEditTestHeaderCommand implements Command {

    private final static String ID_PARAM = "id";
    private final static String TEST_ID_ATTRIBUTE = "testId";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        if(!setTasksAttribute(request)){
            return webPage;
        }

        return new WebPage(WebPage.WebPageBase.TEST_ADMIN_EDIT_HEADER);
    }

    @SuppressWarnings("Duplicates")
    private boolean setTasksAttribute(HttpServletRequest request){
        TestService testService = new TestService();
        String testId = request.getParameter(ID_PARAM);
        if(testId==null)
            return false;
        try{
            Test test = testService.findById(testId);
            request.setAttribute(TEST_ID_ATTRIBUTE, testId);
            request.setAttribute("test", test);
        } catch (UnsuccessfulQueryException | SQLException e) {
            return false;
        }
        return true;
    }
}