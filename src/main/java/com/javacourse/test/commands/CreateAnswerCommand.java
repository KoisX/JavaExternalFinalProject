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

public class CreateAnswerCommand implements Command {

    private static final String LANG_PARAM = "lang";
    private static final String ERROR_PARAM = "error";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Answer answer = constructAnswer(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //checking if model is in valid state
        BeanValidatorConfig<Answer> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(answer)){
            JsonManager.sendSingleMessage(ERROR_PARAM, validator.getErrorMessage(), response);
        }else {
            createAnswer(request, response, answer, lang);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private Answer constructAnswer(Map<String, String[]> parameterMap) {
        Answer answer = new Answer();
        //by default answer is always case insensitive
        answer.setIsCaseSensitive(false);
        answer.setValue(parameterMap.get("value")[0]);
        return answer;
    }

    private void createAnswer(HttpServletRequest request, HttpServletResponse response, Answer answer, String lang) {
        AnswerService answerService = new AnswerService();
        String taskId = request.getParameter("taskId");
        String testId = request.getParameter("testId");
        JsonManager json = new JsonManager(response);

        try {
            if(answerService.create(answer, isAnswerCorrect(request), Long.parseLong(taskId))){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+testId));
            }
        } catch (UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put(ERROR_PARAM, resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }

    private boolean isAnswerCorrect(HttpServletRequest request){
        return request.getParameter("isCorrect")!= null;
    }
}
