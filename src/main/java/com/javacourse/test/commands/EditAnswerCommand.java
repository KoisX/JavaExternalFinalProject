package com.javacourse.test.commands;


import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
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


public class EditAnswerCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Answer answer = constructAnswer(request.getParameterMap());
        boolean isCorrectAnswer = getIsCorrectAnswer(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //checking if model is in valid state
        BeanValidatorConfig<Answer> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(answer)){
            JsonManager.sendSingleMessage("error",validator.getErrorMessage(), response);
        }else {
            editAnswer(request, response, answer, lang, isCorrectAnswer);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private boolean getIsCorrectAnswer(Map<String, String[]> parameterMap) {
        return parameterMap.get("isCorrect") != null;
    }

    private Answer constructAnswer(Map<String, String[]> parameterMap) {
        Answer answer = new Answer();
        answer.setIsCaseSensitive(false);
        answer.setValue(parameterMap.get("value")[0]);
        answer.setId(Long.parseLong(parameterMap.get("id")[0]));
        return answer;
    }

    private void editAnswer(HttpServletRequest request, HttpServletResponse response, Answer answer, String lang, boolean isCorrectAnswer) {
        AnswerService answerService = new AnswerService();
        String taskId = request.getParameter("taskId");
        String testId = request.getParameter("testId");

        JsonManager json = new JsonManager(response);
        try {
            if(answerService.update(answer, isCorrectAnswer, Long.parseLong(taskId))){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId));
            }
        } catch (UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
