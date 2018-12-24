package com.javacourse.test.topic;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.WebPage;
import com.javacourse.shared.web.HttpMethod;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class TopicCommandFactory extends CommandFactory {

    private static final String COMMAND = "command";

    public TopicCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        switch (action){
            case "List":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_TOPICS.getCommand():
                        TopicCommandEnum.DELETE_TOPIC.getCommand();
            case "Create":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_CREATE_PAGE.getCommand() :
                        TopicCommandEnum.ADD_TOPIC.getCommand();
            case "Edit":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_TOPICS.getCommand() :
                        getEditCommand();
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }

    }

    private Command getEditCommand(){
        String param = Optional
                .ofNullable(request.getParameter("command"))
                .orElse("");

        if(param.equals("edit")){
            return TopicCommandEnum.EDIT_TOPIC.getCommand();
        }else {
            return TopicCommandEnum.SHOW_EDIT_PAGE.getCommand();
        }
    }
}
