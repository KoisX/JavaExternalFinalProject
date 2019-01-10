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
import java.util.ResourceBundle;

import static com.javacourse.shared.WebPage.WebPageBase;

public class CreateTopicCommand implements Command {

    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Topic topic = constructTopic(request);
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        BeanValidatorConfig<Topic> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(topic)){
            request.setAttribute(ERROR_REQUEST_MESSAGE, validator.getErrorMessage());
            return new WebPage(WebPageBase.TOPICS_ADMIN_CREATE);
        }

        TopicService topicService = new TopicService();
        return getPageDependingOnWhetherInsertIsSuccessful(request, topic, lang, topicService);
    }

    Topic constructTopic(HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setName(request.getParameter(NAME_PARAM));
        return topic;
    }

    //try to create topic and depending on whether this operation is
    //successful or not return corresponding WebPage
    WebPage getPageDependingOnWhetherInsertIsSuccessful(HttpServletRequest request, Topic topic, String lang, TopicService topicService){
        WebPage webPage = new WebPage(WebPageBase.TOPICS_ACTION);
        try {
            if(topicService.create(topic)){
                return new WebPage(WebPageBase.TOPICS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT);
            }
        } catch ( UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.creationUnsuccessful"));
            return new WebPage(WebPageBase.TOPICS_ADMIN_CREATE);
        }
        return webPage;
    }

}
