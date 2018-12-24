package com.javacourse.test.topic;

import com.javacourse.shared.Command;
import com.javacourse.test.topic.commands.AddTopicCommand;
import com.javacourse.test.topic.commands.DeleteTopicCommand;
import com.javacourse.test.topic.commands.EditTopicCommand;
import com.javacourse.test.topic.commands.ShowTopicsCommand;

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
    };
    Command command;
    public com.javacourse.shared.Command getCommand(){return command;}
}
