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
    },
    STATS{
        {
            this.command = new ShowStatsCommand();
        }
    },
    STATS_DETAILS{
        {
            this.command = new ShowStatsDetailsCommand();
        }
    },
    STATS_EDIT{
        {
            this.command = new EditStatsCommand();
        }
    },
    STATS_DELETE{
        {
            this.command = new DeleteStatsCommand();
        }
    };

    Command command;
    public Command getCommand(){return command;}
}
