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
        WebPage webPage = new WebPage(WebPage.WebPageBase.TASK_ADMIN_EDIT_PAGE);
        TaskService taskService = new TaskService();
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            int testId = Integer.parseInt(request.getParameter("testId"));
            Task task = taskService.findById(taskId);
            request.setAttribute("task", task);
            request.setAttribute("testId", testId);
        } catch (SQLException | UnsuccessfulQueryException | NumberFormatException e) {
            webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }
        return webPage;
    }
}
