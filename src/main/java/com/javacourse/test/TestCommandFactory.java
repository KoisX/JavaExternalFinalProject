package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.web.HttpMethod;
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
            case "Details":
                return TestCommandEnum.SHOW_TEST_DETAILS.getCommand();
            case "Create":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_ADD_TEST.getCommand():
                        TestCommandEnum.ADD_TEST.getCommand();
            case "HeaderEdit":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_EDIT_TEST_HEADER.getCommand():
                        TestCommandEnum.EDIT_HEADER.getCommand();
            case "DescriptionEdit":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_EDIT_TEST_DESCRIPTION.getCommand():
                        TestCommandEnum.EDIT_DESCRIPTION.getCommand();
            case "PrivateStatus":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_TOPICS.getCommand():
                        TestCommandEnum.GRANT_PRIVATE_STATUS.getCommand();
            case "PublicStatus":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_TOPICS.getCommand():
                        TestCommandEnum.GRANT_PUBLIC_STATUS.getCommand();
            case "CreateTask":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_CREATE_TASK.getCommand():
                        TestCommandEnum.CREATE_TASK.getCommand();
            case "TaskDetails":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_TASK_DETAILS.getCommand():
                        TestCommandEnum.CREATE_TASK.getCommand();//TODO: change it!!!
            case "AddAnswer":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_ADD_ANSWER.getCommand():
                        TestCommandEnum.CREATE_ANSWER.getCommand();
            case "EditAnswer":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_EDIT_ANSWER.getCommand()://TODO: change it!!
                        TestCommandEnum.CREATE_TASK.getCommand();//TODO: change it!!!
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }
    }
}
