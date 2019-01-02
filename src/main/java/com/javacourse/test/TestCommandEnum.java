package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.*;
import com.javacourse.test.topic.commands.ShowTopicsCommand;

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
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
