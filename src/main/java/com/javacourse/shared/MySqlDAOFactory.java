package com.javacourse.shared;

import com.javacourse.test.TestDAO;
import com.javacourse.test.TestDAOMySql;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.test.topic.TopicDAOMySql;
import com.javacourse.user.UserDAO;
import com.javacourse.user.UserDAOMySql;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.user.role.RoleDAOMySql;

public class MySqlDAOFactory implements DAOFactory{

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
}
