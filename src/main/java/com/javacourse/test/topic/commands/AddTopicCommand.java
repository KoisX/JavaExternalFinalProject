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

    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.TOPICS_ACTION;

        TopicService topicService = new TopicService();
        Topic topic = constructTopic(request);

        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        Validator validator = BeanValidatorConfig.getValidator(lang);
        Set<ConstraintViolation<Topic>> violations = validator.validate(topic);

        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.TOPICS_ADMIN_CREATE;
        }

        //  А тепер заміни цей весь кусок на tryInsert

        /*webPage = WebPage.TOPICS_ACTION;
        try {
            if(topicService.create(topic)){
                webPage = WebPage.TOPICS_ACTION.setDoRedirect(true);
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.creationUnsuccessful"));
            webPage = WebPage.TOPICS_ADMIN_CREATE;
        }*/
        return webPage;
    }

    private Topic constructTopic(HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setName(request.getParameter(NAME_PARAM));
        return topic;
    }

    private WebPage tryInsert(HttpServletRequest request, Topic topic){
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
