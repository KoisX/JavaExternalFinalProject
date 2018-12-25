package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.shared.Command;
import com.javacourse.utils.ResourceBundleConfig;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AddTopicCommand implements Command {

    private String lang;
    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";

    @Override
    public WebPage execute(HttpServletRequest request) {
        Topic topic = constructTopic(request);
        lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Topic>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(topic);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.TOPICS_ADMIN_CREATE;
        }

        return getPageDependingOnWhetherInsertIsSuccessful(request, topic);
    }

    private Topic constructTopic(HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setName(request.getParameter(NAME_PARAM));
        return topic;
    }

    private WebPage getPageDependingOnWhetherInsertIsSuccessful(HttpServletRequest request, Topic topic){
        WebPage webPage = WebPage.TOPICS_ACTION;
        TopicService topicService = new TopicService();
        try {
            if(topicService.create(topic)){
                return WebPage.TOPICS_ACTION.setDoRedirect(true);
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.creationUnsuccessful"));
            return WebPage.TOPICS_ADMIN_CREATE;
        }
        return webPage;
    }

}
