package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.test.topic.TopicCommandEnum;
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
            case "Tests":
                return TestCommandEnum.SHOW_TESTS_BY_TOPIC.getCommand();
            case "Exam":
                return TestCommandEnum.SHOP_EXAM.getCommand();
            case "Results":
                return TestCommandEnum.CHECK_TEST.getCommand();
            case "Delete":
                return TestCommandEnum.DELETE_TEST.getCommand();
            case "Edit":
                return TestCommandEnum.EDIT_TEST.getCommand();
            case "Create":
                return TestCommandEnum.ADD_TEST.getCommand();
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
