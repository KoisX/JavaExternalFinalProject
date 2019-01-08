package com.javacourse.test.topic;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.web.HttpMethod;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class TopicCommandFactory extends CommandFactory {

    private static final String COMMAND = "command";
    private static final String EDIT_CMD = "edit";

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
                        TopicCommandEnum.CREATE_TOPIC.getCommand();
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
                .ofNullable(request.getParameter(COMMAND))
                .orElse("");

        if(param.equals(EDIT_CMD)){
            return TopicCommandEnum.EDIT_TOPIC.getCommand();
        }else {
            return TopicCommandEnum.SHOW_EDIT_PAGE.getCommand();
        }
    }

}
