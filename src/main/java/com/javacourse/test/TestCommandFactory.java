package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCommandFactory extends CommandFactory {
    public TestCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        switch (action){
            case "Topic":
                return TestCommandEnum.SHOW_TOPICS.getCommand();
            case "Tests":
                return TestCommandEnum.SHOW_TESTS_BY_TOPIC.getCommand();
            case "Exam":
                return TestCommandEnum.SHOP_EXAM.getCommand();
            default:
                return TestCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
