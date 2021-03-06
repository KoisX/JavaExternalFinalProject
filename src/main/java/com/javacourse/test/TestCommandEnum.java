package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.*;

public enum TestCommandEnum {
    SHOW_TESTS_BY_TOPIC{
        {
            this.command = new ShowTestByTopicCommand();
        }
    },
    SHOW_EXAM {
        {
            this.command = new ShowExamCommand();
        }
    },
    CHECK_TEST{
        {
            this.command = new CheckExamCommand();
        }
    },
    DELETE_TEST{
        {
            this.command = new DeleteTestCommand();
        }
    },
    CREATE_TEST {
        {
            this.command = new CreateTestCommand();
        }
    },
    SHOW_ADD_TEST{
        {
            this.command = new ShowAddTestCommand();
        }
    },
    SHOW_TEST_DETAILS{
        {
            this.command = new ShowTestDetailsCommand();
        }
    },
    SHOW_EDIT_TEST_HEADER {
        {
            this.command = new ShowEditTestHeaderCommand();
        }
    },
    EDIT_HEADER{
        {
            this.command = new EditHeaderCommand();
        }
    },
    SHOW_EDIT_TEST_DESCRIPTION {
        {
            this.command = new ShowEditTestDescriptionCommand();
        }
    },
    EDIT_DESCRIPTION{
        {
            this.command = new EditDescriptionCommand();
        }
    },
    GRANT_PRIVATE_STATUS{
        {
            this.command = new GrantPrivateStatusCommand();
        }
    },
    GRANT_PUBLIC_STATUS{
        {
            this.command = new GrantPublicStatusCommand();
        }
    },
    SHOW_CREATE_TASK{
        {
            this.command = new ShowCreateTaskCommand();
        }
    },
    CREATE_TASK{
        {
            this.command = new CreateTaskCommand();
        }
    },
    SHOW_TASK_DETAILS{
        {
            this.command = new ShowTaskDetailsCommand();
        }
    },
    SHOW_ADD_ANSWER{
        {
            this.command = new ShowAddAnswerCommand();
        }
    },
    SHOW_EDIT_ANSWER{
        {
            this.command = new ShowEditAnswerCommand();
        }
    },
    CREATE_ANSWER{
        {
            this.command = new CreateAnswerCommand();
        }
    },
    DELETE_ANSWER{
        {
            this.command = new DeleteAnswerCommand();
        }
    },
    DELETE_TASK {
        {
            this.command = new DeleteTaskCommand();
        }
    },
    EDIT_TASK {
        {
            this.command = new EditTaskCommand();
        }
    },
    EDIT_ANSWER {
        {
            this.command = new EditAnswerCommand();
        }
    };

    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
