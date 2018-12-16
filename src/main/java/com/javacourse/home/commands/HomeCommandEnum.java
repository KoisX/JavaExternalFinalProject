package com.javacourse.home.commands;

import com.javacourse.shared.Command;

public enum  HomeCommandEnum {
    INDEX{
        {
            this.command = new ShowIndexCommand();
        }
    },
    ABOUT{
        {
            this.command = new ShowAboutCommand();
        }
    },
    RULES{
        {
            this.command = new ShowRulesCommand();
        }
    };

    Command command;
    public Command getCommand(){return command;}
}