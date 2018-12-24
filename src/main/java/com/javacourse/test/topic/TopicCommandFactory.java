package com.javacourse.test.topic;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.test.TestCommandEnum;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicCommandFactory extends CommandFactory {
    public TopicCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        switch (action){
            case "Topic":
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
