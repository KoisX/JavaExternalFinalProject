package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.JsonManager;
import com.javacourse.utils.ResourceBundleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class EditTaskCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Task task = constructTaskIfPossible(request.getParameterMap());
        if(task==null){
            JsonManager.sendSingleMessage("error", "Error occurred", response);
            return WebPage.STAND_STILL_PAGE;
        }

        //checking if model is in valid state
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        BeanValidatorConfig<Task> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(task)){
            JsonManager.sendSingleMessage("error", validator.getErrorMessage(), response);
        }else {
            editTask(request, response, task, lang);
        }

        return WebPage.STAND_STILL_PAGE;
    }

    private Task constructTaskIfPossible(Map<String, String[]> parameterMap) {
        Task task = new Task();
        task.setQuestion(parameterMap.get("question")[0]);
        try {
            task.setPrice(Integer.parseInt(parameterMap.get("price")[0]));
            task.setId(Integer.parseInt(parameterMap.get("taskId")[0]));
        } catch (NumberFormatException e) {
            return null;
        }
        return task;
    }

    private void editTask(HttpServletRequest request, HttpServletResponse response, Task task, String lang) {
        TaskService taskService = new TaskService();
        String testId = request.getParameter("testId");
        JsonManager json = new JsonManager(response);
        try {
            if(taskService.update(task)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId).toString());
            }
        } catch (UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
