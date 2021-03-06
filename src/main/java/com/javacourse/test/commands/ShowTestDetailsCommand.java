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

public class ShowTestDetailsCommand implements Command {

    private final static String ID_PARAM = "id";
    private final static String TASKS_ATTRIBUTE = "tasks";
    private final static String TEST_ID_ATTRIBUTE = "testId";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        TaskService taskService = new TaskService();
        TestService testService = new TestService();
        if(!setTasksAttribute(request, testService, taskService)){
            return webPage;
        }
        return new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_PAGE);
    }

    boolean setTasksAttribute(HttpServletRequest request, TestService testService, TaskService taskService){
        String testId = request.getParameter(ID_PARAM);
        if(testId==null)
            return false;
        try{
            List<Task> tasks = taskService.findTasksByTestId(testId);
            Test test = testService.findById(testId);
            request.setAttribute(TASKS_ATTRIBUTE, tasks);
            request.setAttribute(TEST_ID_ATTRIBUTE, testId);
            request.setAttribute("test", test);
        } catch (UnsuccessfulQueryException  e) {
            return false;
        }
        return true;
    }
}
