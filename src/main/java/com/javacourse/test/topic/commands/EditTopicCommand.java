package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.shared.Command;
import com.javacourse.utils.ResourceBundleConfig;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class EditTopicCommand implements Command {

    private String lang;
    private ResourceBundle resourceBundle;
    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String TOPIC_PARAM = "topic";
    private static final String ID = "id";
    private static final String ERROR_REQUEST_MESSAGE = "error";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Topic topic = constructTopic(request);
        initLanguageFields((String)request.getSession().getAttribute(LANG_PARAM));

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Topic>> violations = BeanValidatorConfig
                                            .getValidator(lang)
                                            .validate(topic);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.TOPICS_ADMIN_FORWARD_EDIT;
        }

        return getPageBasedOnWhetherEditIsSuccessful(request, topic);
    }

    private Topic constructTopic(HttpServletRequest request) {
        Topic topic = new Topic();

        /*If we get a request from topics page,
        * we get the values of the properties from request form params,
        * but if the edit operation is unsuccessful and we get the request
        * from the edit page, we get these values from the attribute list*/

        String name = Optional
                .ofNullable(request.getParameter(NAME_PARAM))
                .orElse((String) request.getAttribute(NAME_PARAM));

        String id = Optional
                .ofNullable(request.getParameter(ID))
                .orElse((String) request.getAttribute(ID));

        topic.setName(name);
        topic.setId(Long.parseLong(id));
        return topic;
    }

    private void initLanguageFields(String language){
        lang = language;
        resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
    }

    private WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, Topic topic){
        WebPage webPage = WebPage.TOPICS_FORWARD_ACTION;
        TopicService topicService = new TopicService();
        try {
            if(topicService.update(topic)){
                webPage = WebPage.TOPICS_REDIRECT_ACTION;
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            setErrorRequestAttributes(request, topic);
            webPage = WebPage.TOPICS_ADMIN_FORWARD_EDIT;
        }
        return webPage;
    }

    private void setErrorRequestAttributes(HttpServletRequest request, Topic topic){
        request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.editUnsuccessful"));
        request.setAttribute(NAME_PARAM, topic.getName());
        request.setAttribute(ID, topic.getId());
        request.setAttribute(TOPIC_PARAM, topic);
    }

}
