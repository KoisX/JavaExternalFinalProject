package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.test.topic.commands.ShowTopicsCommand;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowExamCommand implements Command {

    private final static Logger logger;
    private final static String ID_PARAM = "id";
    private final static String TASKS_ATTRIBUTE = "tasks";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.ERROR_ACTION;
        if(!setTasksAttribute(request)){
            return webPage;
        }
        return WebPage.EXAM_USER_PAGE;
    }

    private boolean setTasksAttribute(HttpServletRequest request){
        TaskService taskService = new TaskService();
        String testId = request.getParameter(ID_PARAM);
        if(testId==null)
            return false;
        try{
            List<Task> tasks = taskService.findTasksByTestId(testId);
            request.setAttribute(TASKS_ATTRIBUTE, tasks);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
