package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.answer.Answer;
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

public class EditHeaderCommand implements Command {

    //this class need to be heavily refactored. Its just a working template

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Test test = constructTest(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Test>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(test);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            showErrorResult(response, violations.iterator().next().getMessage());
            return WebPage.STAND_STILL_PAGE;
        }
        return getResponse(request, response, test, lang);
    }

    private Test constructTest(Map<String, String[]> parameterMap) {
        //We need this value to validate those fields, which dont really matter
        final String VALID_FIELD = "   ";
        Test test = new Test();
        test.setDescription(VALID_FIELD);
        test.setHeader(parameterMap.get("header")[0]);
        return test;
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

    private WebPage getResponse(HttpServletRequest request, HttpServletResponse response, Test test, String lang) {
        TestService testService = new TestService();
        String id = request.getParameter("id");

        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if(testService.updateHeader(test.getHeader(), Long.parseLong(id))){
                jsonResponse.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+id).toString());
            }
        } catch (UnsuccessfulQueryException | SQLException | NumberFormatException e) {
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

