package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.topic.Topic;
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

public class AddTestCommand implements Command {

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
            showErrorResult(response, violations.iterator().next().getMessage());
            return WebPage.STAND_STILL_PAGE;
        }

        return getPageDependingOnWhetherInsertIsSuccessful(request, response, test);
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

    //try to create topic and depending on whether this operation is
    //successful or not return corresponding WebPage
    private WebPage getPageDependingOnWhetherInsertIsSuccessful(HttpServletRequest request, HttpServletResponse response, Test test){
        String topicId = request.getParameter("id");
        TestService testService = new TestService();

        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if(testService.create(test)){
                jsonResponse.put("url", new WebPage(WebPage.WebPageBase.TESTS_ACTION)
                        .setQueryString("?id="+topicId).toString());
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
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
