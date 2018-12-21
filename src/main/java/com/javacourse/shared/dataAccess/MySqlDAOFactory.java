package com.javacourse.shared.dataAccess;

import com.javacourse.stats.StatsDAO;
import com.javacourse.stats.StatsDAOMySql;
import com.javacourse.test.TestDAO;
import com.javacourse.test.TestDAOMySql;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.test.answer.AnswerDAOMySql;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.test.topic.TopicDAOMySql;
import com.javacourse.user.UserDAO;
import com.javacourse.user.UserDAOMySql;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.user.role.RoleDAOMySql;

/**
 * The concrete implementation of the DAOFactory
 * for the MySQL relational database
 */
public class MySqlDAOFactory implements DAOFactory {

    @Override
    public UserDAO createUserDAO(SqlConnection sqlConnection) {
        return new UserDAOMySql(sqlConnection.getConnection());
    }

    @Override
    public RoleDAO createRoleDAO(SqlConnection connection) {
        return new RoleDAOMySql(connection.getConnection());
    }

    @Override
    public TopicDAO createTopicDAO(SqlConnection connection) {
        return new TopicDAOMySql(connection.getConnection());
    }

    @Override
    public TestDAO createTestDAO(SqlConnection connection) {
        return new TestDAOMySql(connection.getConnection());
    }

    @Override
    public TaskDAO createTaskDAO(SqlConnection connection) {
        return new TaskDAOMySql(connection.getConnection());
    }

    @Override
    public AnswerDAO createAnswerDAO(SqlConnection connection) {
        return new AnswerDAOMySql(connection.getConnection());
    }

    @Override
    public StatsDAO createStatsDAO(SqlConnection connection) {
        return new StatsDAOMySql(connection.getConnection());
    }
}
