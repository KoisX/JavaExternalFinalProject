package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.topic.Topic;
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

public class CreateTestCommand implements Command {

    private String lang;
    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Test test = constructTest(request.getParameterMap());
        lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Test>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(test);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            JsonManager.sendError("error", violations.iterator().next().getMessage(), response);
        }else {
            createTest(request, response, test);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private Test constructTest(Map<String, String[]> parameterMap) {
        Test test = new Test();
        Topic topic = new Topic();
        topic.setId(Long.parseLong(parameterMap.get("id")[0]));
        test.setHeader(parameterMap.get("header")[0]);
        test.setDescription(parameterMap.get("description")[0]);
        test.setTopic(topic);
        /*By default, a newly created test is private*/
        test.setIsPublic(false);
        return test;
    }

    private void createTest(HttpServletRequest request, HttpServletResponse response, Test test){
        String topicId = request.getParameter("id");
        TestService testService = new TestService();
        JsonManager json = new JsonManager(response);
        try {
            if(testService.create(test)){
                json.put("url", new WebPage(WebPage.WebPageBase.TESTS_ACTION)
                        .setQueryString("?id="+topicId));
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
