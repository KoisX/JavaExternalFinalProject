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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.ResourceBundle;

public class CreateTestCommand implements Command {

    private static final String LANG_PARAM = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Test test = constructTest(request.getParameterMap());
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        BeanValidatorConfig<Test> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(test)){
            JsonManager.sendSingleMessage("error", validator.getErrorMessage(), response);
        }else {
            createTest(request, response, test, lang);
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

    private void createTest(HttpServletRequest request, HttpServletResponse response, Test test, String lang){
        String topicId = request.getParameter("id");
        TestService testService = new TestService();
        JsonManager json = new JsonManager(response);
        try {
            if(testService.create(test)){
                json.put("url", new WebPage(WebPage.WebPageBase.TESTS_ACTION)
                        .setQueryString("?id="+topicId));
            }
        } catch ( UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }
}
