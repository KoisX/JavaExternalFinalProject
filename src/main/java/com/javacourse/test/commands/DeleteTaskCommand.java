package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteTaskCommand implements Command {

    private final static Logger logger;
    //logger configuration
    static {
        logger = LogConfigurator.getLogger(DeleteTaskCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String taskId = request.getParameter("taskId");
        String testId = request.getParameter("testId");
        TaskService taskService = new TaskService();
        try {
            if(taskService.delete(taskId))
                return new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT)
                        .setQueryString("?id="+testId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
        }
        //in case delete is unsuccessful
        return new WebPage(WebPage.WebPageBase.ERROR_ACTION);
    }
}
