package com.javacourse.home.commands;

import com.javacourse.shared.Command;

public enum  HomeCommandEnum {
    SHOW_INDEX {
        {
            this.command = new ShowIndexCommand();
        }
    },
    SHOW_ABOUT {
        {
            this.command = new ShowAboutCommand();
        }
    },
    SHOW_RULES {
        {
            this.command = new ShowRulesCommand();
        }
    },
    SHOW_STATS {
        {
            this.command = new ShowStatsCommand();
        }
    },
    SHOW_STATS_DETAILS {
        {
            this.command = new ShowStatsDetailsCommand();
        }
    },
    EDIT_STATS {
        {
            this.command = new EditStatsCommand();
        }
    },
    DELETE_STATS {
        {
            this.command = new DeleteStatsCommand();
        }
    };

    Command command;
    public Command getCommand(){return command;}
}
