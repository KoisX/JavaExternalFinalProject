package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.JsonManager;
import com.javacourse.utils.ResourceBundleConfig;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@SuppressWarnings("ALL")
public class CreateTaskCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Task task = null;
        try {
            task = constructTask(request.getParameterMap());
        } catch (NumberFormatException e) {
            JsonManager.sendSingleMessage("error", "Error occurred", response);
            return WebPage.STAND_STILL_PAGE;
        }
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        BeanValidatorConfig<Task> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(task)){
            JsonManager.sendSingleMessage("error", validator.getErrorMessage(), response);
        }else {
            createTask(request, response, task, lang);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private Task constructTask(Map<String, String[]> parameterMap) {
        Task task = new Task();
        task.setQuestion(parameterMap.get("question")[0]);
        task.setPrice(Integer.parseInt(parameterMap.get("price")[0]));
        task.setTestId(Integer.parseInt(parameterMap.get("id")[0]));
        return task;
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response, Task task, String lang) {
        TaskService taskService = new TaskService();
        String testId = request.getParameter("id");
        JsonManager json = new JsonManager(response);

        try {
            if(taskService.create(task)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId).toString());
            }
        } catch (UnsuccessfulQueryException | SQLException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
