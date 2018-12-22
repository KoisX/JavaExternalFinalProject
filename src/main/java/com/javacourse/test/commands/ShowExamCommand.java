package com.javacourse.test.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowExamCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        TaskService taskService = new TaskService();
        String testId = request.getParameter("id");
        if(testId==null)
            return WebPage.ERROR_ACTION;
        List<Task> tasks;
        try{
            tasks = taskService.findTasksByTestId(testId);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return WebPage.ERROR_ACTION;
        }
        request.setAttribute("tasks", tasks);
        return WebPage.EXAM_USER_PAGE;
    }
}
