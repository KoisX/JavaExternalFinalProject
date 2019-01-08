package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.web.HttpMethod;
import com.javacourse.test.topic.TopicCommandEnum;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCommandFactory extends CommandFactory {

    private static final String COMMAND = "command";
    private static final String EDIT_CMD = "edit";
    private static final String DELETE_CMD = "delete";

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
                return TestCommandEnum.SHOW_EXAM.getCommand();
            case "Results":
                return TestCommandEnum.CHECK_TEST.getCommand();
            case "Delete":
                return TestCommandEnum.DELETE_TEST.getCommand();
            case "Details":
                return TestCommandEnum.SHOW_TEST_DETAILS.getCommand();
            case "Create":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_ADD_TEST.getCommand():
                        TestCommandEnum.CREATE_TEST.getCommand();
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
                        getTaskEditCommand(request);
            case "AddAnswer":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_ADD_ANSWER.getCommand():
                        TestCommandEnum.CREATE_ANSWER.getCommand();
            case "EditAnswer":
                return HttpMethod.isGet(request.getMethod()) ?
                        TestCommandEnum.SHOW_EDIT_ANSWER.getCommand():
                        TestCommandEnum.EDIT_ANSWER.getCommand();
            case "DeleteAnswer":
                return HttpMethod.isGet(request.getMethod()) ?
                        TopicCommandEnum.SHOW_TOPICS.getCommand()://only post method is allowed
                        TestCommandEnum.DELETE_ANSWER.getCommand();
            default:
                return TopicCommandEnum.SHOW_TOPICS.getCommand();
        }
    }

    Command getTaskEditCommand(HttpServletRequest request){
        String commandParam = request.getParameter("command");
        Command command = TopicCommandEnum.SHOW_TOPICS.getCommand();//default page
        if(commandParam.equals("edit")){
            command = TestCommandEnum.EDIT_TASK.getCommand();
        }else if(commandParam.equals("delete")){
            command = TestCommandEnum.DELETE_TASK.getCommand();
        }
        return command;
    }
}
