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
import java.util.Optional;
import java.util.ResourceBundle;

import static com.javacourse.shared.WebPage.WebPageBase;

public class EditTopicCommand implements Command {

    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String TOPIC_PARAM = "topic";
    private static final String ID = "id";
    private static final String ERROR_REQUEST_MESSAGE = "error";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Topic topic = constructTopic(request);
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        ResourceBundle resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
        BeanValidatorConfig<Topic> validator = new BeanValidatorConfig<>(lang);
        //set error message if model is not valid
        if(!validator.isValid(topic)){
            request.setAttribute(ERROR_REQUEST_MESSAGE, validator.getErrorMessage());
            return new WebPage(WebPageBase.TOPICS_ADMIN_EDIT);
        }
        TopicService topicService = new TopicService();

        return getPageBasedOnWhetherEditIsSuccessful(request, topic, topicService, resourceBundle);
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


     WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, Topic topic, TopicService topicService, ResourceBundle bundle){
        WebPage webPage = new WebPage(WebPageBase.TOPICS_ACTION);
        try {
            if(topicService.update(topic)){
                webPage = new WebPage(WebPageBase.TOPICS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT);
            }
        } catch ( UnsuccessfulQueryException e) {
            setErrorRequestAttributes(request, topic, bundle);
            webPage = new WebPage(WebPageBase.TOPICS_ADMIN_EDIT);
        }
        return webPage;
    }

    private void setErrorRequestAttributes(HttpServletRequest request, Topic topic, ResourceBundle bundle){
        request.setAttribute(ERROR_REQUEST_MESSAGE, bundle.getString("msg.editUnsuccessful"));
        request.setAttribute(NAME_PARAM, topic.getName());
        request.setAttribute(ID, topic.getId());
        request.setAttribute(TOPIC_PARAM, topic);
    }

}
