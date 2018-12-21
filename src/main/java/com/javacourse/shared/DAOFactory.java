package com.javacourse.shared;

import com.javacourse.test.TestDAO;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.RoleDAO;

public interface DAOFactory {
    UserDAO createUserDAO(SqlConnection connection);
    RoleDAO createRoleDAO(SqlConnection connection);
    TopicDAO createTopicDAO(SqlConnection connection);
    TestDAO createTestDAO(SqlConnection connection);
    TaskDAO createTaskDAO(SqlConnection connection);
    AnswerDAO createAnswerDAO(SqlConnection connection);
}
