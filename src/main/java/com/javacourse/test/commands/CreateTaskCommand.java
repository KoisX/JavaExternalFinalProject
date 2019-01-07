package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.BeanValidatorConfig;
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
            showErrorResult(response, "Error occurred");
            return WebPage.STAND_STILL_PAGE;
        }
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Task>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(task);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            showErrorResult(response, violations.iterator().next().getMessage());
            return WebPage.STAND_STILL_PAGE;
        }

        return getResponse(request, response, task, lang);
    }

    private Task constructTask(Map<String, String[]> parameterMap) {
        Task task = new Task();
        task.setQuestion(parameterMap.get("question")[0]);
        task.setPrice(Integer.parseInt(parameterMap.get("price")[0]));
        task.setTestId(Integer.parseInt(parameterMap.get("id")[0]));
        return task;
    }

    @SuppressWarnings("Duplicates")
    private void showErrorResult(HttpServletResponse response, String error) {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("error", error);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
    }

    private WebPage getResponse(HttpServletRequest request, HttpServletResponse response, Task task, String lang) {
        TaskService taskService = new TaskService();
        String testId = request.getParameter("id");

        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if(taskService.create(task)){
                jsonResponse.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId).toString());
            }
        } catch (UnsuccessfulQueryException | SQLException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            jsonResponse.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
        return WebPage.STAND_STILL_PAGE;
    }
}
