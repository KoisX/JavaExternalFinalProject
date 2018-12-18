package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.ShowTestByTopicCommand;
import com.javacourse.test.commands.ShowTopicsCommand;

public enum TestCommandEnum {
    SHOW_TOPICS{
        {
          this.command = new ShowTopicsCommand();
        }
    },
    SHOW_TESTS_BY_TOPIC{
        {
            this.command = new ShowTestByTopicCommand();
        }
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
