package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.test.topic.commands.ShowTopicsCommand;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowExamCommand implements Command {

    private final static Logger logger;
    private final static String ID_PARAM = "id";
    private final static String TASKS_ATTRIBUTE = "tasks";
    private final static String TEST_ID_ATTRIBUTE = "testId";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPageBase.ERROR_ACTION);
        if(!setTasksAttribute(request) || !isTestAccessibleForUser(request)){
            return webPage;
        }
        return new WebPage(WebPageBase.EXAM_USER_PAGE);
    }

    private boolean setTasksAttribute(HttpServletRequest request){
        TaskService taskService = new TaskService();
        String testId = request.getParameter(ID_PARAM);
        if(testId==null)
            return false;
        try{
            List<Task> tasks = taskService.findTasksByTestId(testId);
            request.setAttribute(TASKS_ATTRIBUTE, tasks);
            request.setAttribute(TEST_ID_ATTRIBUTE, testId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean isTestAccessibleForUser(HttpServletRequest request){
        TestService testService = new TestService();
        try {
            Test test = testService.findById(request.getParameter(ID_PARAM));
            return test.getIsPublic() || (((Role) request.getSession().getAttribute("role")) == Role.ADMIN);
        } catch (UnsuccessfulQueryException e) {
            return false;
        }
    }
}
