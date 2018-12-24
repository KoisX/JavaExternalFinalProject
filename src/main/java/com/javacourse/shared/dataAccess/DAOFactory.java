package com.javacourse.shared.dataAccess;

import com.javacourse.stats.StatsDAO;
import com.javacourse.test.TestDAO;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.RoleDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Basic interface for implementing DAO factories,
 * so that switching between databases would be much easier
 */
public interface DAOFactory {
    UserDAO createUserDAO(DBConnection connection);
    RoleDAO createRoleDAO(DBConnection connection);
    TopicDAO createTopicDAO(DBConnection connection);
    TestDAO createTestDAO(DBConnection connection);
    TaskDAO createTaskDAO(DBConnection connection);
    AnswerDAO createAnswerDAO(DBConnection connection);
    StatsDAO createStatsDAO(DBConnection connection);
    DBConnection createConnection() throws SQLException;
}
