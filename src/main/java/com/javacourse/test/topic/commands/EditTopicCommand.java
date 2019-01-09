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
        BeanValidatorConfig<Topic> validator = new BeanValidatorConfig<>(lang);
        //set error message if model is not valid
        if(!validator.isValid(topic)){
            request.setAttribute(ERROR_REQUEST_MESSAGE, validator.getErrorMessage());
            return new WebPage(WebPageBase.TOPICS_ADMIN_EDIT);
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
        resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
    }

    private WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, Topic topic){
        WebPage webPage = new WebPage(WebPageBase.TOPICS_ACTION);
        TopicService topicService = new TopicService();
        try {
            if(topicService.update(topic)){
                webPage = new WebPage(WebPageBase.TOPICS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT);
            }
        } catch ( UnsuccessfulQueryException e) {
            setErrorRequestAttributes(request, topic);
            webPage = new WebPage(WebPageBase.TOPICS_ADMIN_EDIT);
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
