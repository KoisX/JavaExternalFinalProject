package com.javacourse.test;

import com.javacourse.home.commands.HomeCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;

public class TestCommandFactory extends CommandFactory {
    public TestCommandFactory(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request.getRequestURI());
        String action = marshaller.getAction();
        switch (action){
            case "Topic":
                return TestCommandEnum.SHOW_TOPICS.getCommand();
            default:
                return TestCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
