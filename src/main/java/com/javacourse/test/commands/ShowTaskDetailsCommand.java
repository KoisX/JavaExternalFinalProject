package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowTaskDetailsCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TaskService taskService = new TaskService();
        return getPageDependingOnWhetherOperationIsSuccessful(request, taskService);
    }

    WebPage getPageDependingOnWhetherOperationIsSuccessful(HttpServletRequest request, TaskService taskService){
        WebPage webPage = new WebPage(WebPage.WebPageBase.TASK_ADMIN_EDIT_PAGE);
        try {
            String taskId = request.getParameter("taskId");
            String testId = request.getParameter("testId");
            Task task = taskService.findById(taskId);
            request.setAttribute("task", task);
            request.setAttribute("testId", testId);
        } catch ( UnsuccessfulQueryException | NumberFormatException e) {
            webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }
        return webPage;
    }
}
