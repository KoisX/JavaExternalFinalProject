package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
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

public class EditHeaderCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Test test = constructTest(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        BeanValidatorConfig<Test> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(test)){
            JsonManager.sendSingleMessage("error", validator.getErrorMessage(), response);
        }else {
            editHeader(request, response, test, lang);
        }
        return WebPage.STAND_STILL_PAGE;
    }

    private Test constructTest(Map<String, String[]> parameterMap) {
        //We need this value to validate those fields, which dont really matter
        final String VALID_FIELD = "   ";
        Test test = new Test();
        test.setDescription(VALID_FIELD);
        test.setHeader(parameterMap.get("header")[0]);
        return test;
    }

    private void editHeader(HttpServletRequest request, HttpServletResponse response, Test test, String lang) {
        TestService testService = new TestService();
        String id = request.getParameter("id");
        JsonManager json = new JsonManager(response);
        try {
            if(testService.updateHeader(test.getHeader(), id)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+id).toString());
            }
        } catch (UnsuccessfulQueryException | NumberFormatException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }

}

