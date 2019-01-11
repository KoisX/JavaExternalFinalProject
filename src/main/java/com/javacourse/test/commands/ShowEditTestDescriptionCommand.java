package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.task.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowEditTestDescriptionCommand implements Command {
    private final static String ID_PARAM = "id";
    private final static String TEST_ID_ATTRIBUTE = "testId";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TestService testService = new TestService();
        return getPageDependingOnWhetherOperationIsSuccessful(request, testService);
    }

    WebPage getPageDependingOnWhetherOperationIsSuccessful(HttpServletRequest request, TestService testService){
        WebPage webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        if(!setTasksAttribute(request, testService)){
            return webPage;
        }

        return new WebPage(WebPage.WebPageBase.TEST_ADMIN_EDIT_DESCRIPTION);
    }


    private boolean setTasksAttribute(HttpServletRequest request, TestService testService){
        String testId = request.getParameter(ID_PARAM);
        if(testId==null)
            return false;
        try{
            Test test = testService.findById(testId);
            setRequestAttributes(test, request);
        } catch (UnsuccessfulQueryException e) {
            return false;
        }
        return true;
    }

    private void setRequestAttributes(Test test, HttpServletRequest request){
        request.setAttribute(TEST_ID_ATTRIBUTE, test.getId());
        request.setAttribute("test", test);
    }
}
