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
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
