package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.test.commands.*;

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
    },
    SHOP_EXAM{
        {
            this.command = new ShowExamCommand();
        }
    },
    DELETE_TOPIC{
        {
            this.command = new DeleteTopic();
        }
    },
    EDIT_TOPIC{
        {
            this.command = new EditTopic();
        }
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
