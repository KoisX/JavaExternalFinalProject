package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.test.topic.Topic;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.ResourceBundleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class AddTestCommand implements Command {

    private String lang;
    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";

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
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return new WebPage(WebPage.WebPageBase.TEST_ADMIN_ADD_PAGE)
                    .setQueryString("?id="+request.getParameter("id"));
            //TODO: fix it!
        }

        return getPageDependingOnWhetherInsertIsSuccessful(request, test);
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
    private WebPage getPageDependingOnWhetherInsertIsSuccessful(HttpServletRequest request, Test test){
        WebPage webPage = new WebPage(WebPage.WebPageBase.TEST_ADMIN_ADD_PAGE)
                .setQueryString("?id="+request.getParameter("id"));
        TestService testService = new TestService();
        try {
            if(testService.create(test)){
                return new WebPage(WebPage.WebPageBase.TESTS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT)
                        .setQueryString("?id="+request.getParameter("id"));
            }
            else {
                setRequestParams(request, test);
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.creationUnsuccessful"));
            return new WebPage(WebPage.WebPageBase.TEST_ADMIN_ADD_PAGE)
                    .setQueryString("?id="+request.getParameter("id"));
        }
        return webPage;
    }

    private void setRequestParams(HttpServletRequest request, Test test){
        request.setAttribute("id", test.getTopic().getId());
    }
}
