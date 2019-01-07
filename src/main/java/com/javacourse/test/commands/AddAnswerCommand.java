package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
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

public class AddAnswerCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Answer answer = constructAnswer(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Answer>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(answer);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            showErrorResult(response, violations.iterator().next().getMessage());
            return WebPage.STAND_STILL_PAGE;
        }

        return getResponse(request, response, answer, lang);
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

    private Answer constructAnswer(Map<String, String[]> parameterMap) {
        Answer answer = new Answer();
        answer.setIsCaseSensitive(false);//by default answer is always case insensitive
        answer.setValue(parameterMap.get("value")[0]);
        return answer;
    }

    private WebPage getResponse(HttpServletRequest request, HttpServletResponse response, Answer answer, String lang) {
        AnswerService answerService = new AnswerService();
        String taskId = request.getParameter("taskId");
        String testId = request.getParameter("testId");

        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if(answerService.create(answer, isAnswerCorrect(request), Long.parseLong(taskId))){
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

    private boolean isAnswerCorrect(HttpServletRequest request){
        return request.getParameter("isCorrect")!= null;
    }
}
