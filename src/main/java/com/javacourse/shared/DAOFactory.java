package com.javacourse.shared;

import com.javacourse.stats.StatsDAO;
import com.javacourse.test.TestDAO;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.RoleDAO;

/**
 * Basic interface for implementing DAO factories,
 * so that switching between databases would be much easier
 */
public interface DAOFactory {
    UserDAO createUserDAO(SqlConnection connection);
    RoleDAO createRoleDAO(SqlConnection connection);
    TopicDAO createTopicDAO(SqlConnection connection);
    TestDAO createTestDAO(SqlConnection connection);
    TaskDAO createTaskDAO(SqlConnection connection);
    AnswerDAO createAnswerDAO(SqlConnection connection);
    StatsDAO createStatsDAO(SqlConnection connection);
}
