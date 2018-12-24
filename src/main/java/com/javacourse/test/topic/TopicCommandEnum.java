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
    ADD_TOPIC{
        {
            this.command = new AddTopicCommand();
        }
    },
    SHOW_CREATE_PAGE{
        {
            this.command = new ShowCreatePageCommand();
        }
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
