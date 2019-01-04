package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.*;

public enum TestCommandEnum {
    SHOW_TESTS_BY_TOPIC{
        {
            this.command = new ShowTestByTopicCommand();
        }
    },
    SHOP_EXAM{
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
    ADD_TEST{
        {
            this.command = new AddTestCommand();
        }
    },
    EDIT_TEST{
        {
            this.command = new EditTestCommand();
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
    };

    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
