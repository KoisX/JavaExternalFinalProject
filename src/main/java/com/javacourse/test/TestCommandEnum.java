package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.ShowTopicsCommand;

public enum TestCommandEnum {
    SHOW_TOPICS{
        {
          this.command = new ShowTopicsCommand();
        }
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
