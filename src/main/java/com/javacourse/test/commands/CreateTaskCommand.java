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
import java.util.Map;
import java.util.ResourceBundle;

public class CreateTaskCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Task task = constructTaskIfPossible(request.getParameterMap());
        TaskService taskService = new TaskService();

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
            createTask(request, response, task, lang, taskService);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private Task constructTaskIfPossible(Map<String, String[]> parameterMap) throws NumberFormatException{
        Task task = new Task();
        task.setQuestion(parameterMap.get("question")[0]);
        try {
            task.setPrice(Integer.parseInt(parameterMap.get("price")[0]));
            task.setTestId(Integer.parseInt(parameterMap.get("id")[0]));
        } catch (NumberFormatException e) {
            return null;
        }
        return task;
    }

    void createTask(HttpServletRequest request, HttpServletResponse response, Task task, String lang, TaskService taskService) {
        String testId = request.getParameter("id");
        JsonManager json = new JsonManager(response);

        try {
            if(taskService.create(task)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId).toString());
            }
        } catch (UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
