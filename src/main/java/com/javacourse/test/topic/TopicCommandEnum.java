package com.javacourse.test.topic;

import com.javacourse.shared.Command;
import com.javacourse.test.topic.commands.*;

public enum TopicCommandEnum {
    SHOW_TOPICS{
        {
            this.command = new ShowTopicsCommand();
        }
    },
    DELETE_TOPIC{
        {
            this.command = new DeleteTopicCommand();
        }
    },
    EDIT_TOPIC{
        {
            this.command = new EditTopicCommand();
        }
    },
    CREATE_TOPIC {
        {
            this.command = new CreateTopicCommand();
        }
    },
    SHOW_CREATE_PAGE{
        {
            this.command = new ShowCreatePageCommand();
        }
    },
    SHOW_EDIT_PAGE{
        {
            this.command = new ShowEditPageCommand();
        }
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
