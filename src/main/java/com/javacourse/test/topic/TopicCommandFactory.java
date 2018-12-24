package com.javacourse.test.topic;

import com.javacourse.shared.CRUDCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.test.TestCommandEnum;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicCommandFactory extends CommandFactory {

    private static final String COMMAND = "command";

    public TopicCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        String action = request.getParameter(COMMAND);
        if(action==null)
            return TopicCommandEnum.SHOW_TOPICS.getCommand();
        switch (action){
            case "delete":
                return TopicCommandEnum.DELETE_TOPIC.getCommand();
            case "edit":
                return TopicCommandEnum.EDIT_TOPIC.getCommand();
            case "create":
                return  TopicCommandEnum.ADD_TOPIC.getCommand();
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
