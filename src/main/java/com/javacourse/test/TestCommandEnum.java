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
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
