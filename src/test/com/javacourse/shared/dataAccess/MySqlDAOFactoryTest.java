package com.javacourse.shared.dataAccess;

import com.javacourse.stats.StatsDAO;
import com.javacourse.stats.StatsDAOMySql;
import com.javacourse.test.TestDAO;
import com.javacourse.test.TestDAOMySql;
import com.javacourse.test.answer.Answer;
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
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MySqlDAOFactoryTest {

    MySqlDAOFactory factory;
    SqlConnection sqlConnection;
    Connection connection;

    @Before
    public void setUp() throws Exception {
        factory = new MySqlDAOFactory();
        sqlConnection = mock(SqlConnection.class);
        connection = mock(Connection.class);
    }

    @Test
    public void createUserDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        UserDAO expected = new UserDAOMySql(sqlConnection.getConnection());
        UserDAO actual = factory.createUserDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createRoleDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        RoleDAO expected = new RoleDAOMySql(sqlConnection.getConnection());
        RoleDAO actual = factory.createRoleDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createTopicDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        TopicDAO expected = new TopicDAOMySql(sqlConnection.getConnection());
        TopicDAO actual = factory.createTopicDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createTestDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        TestDAO expected = new TestDAOMySql(sqlConnection.getConnection());
        TestDAO actual = factory.createTestDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createTaskDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        TaskDAO expected = new TaskDAOMySql(sqlConnection.getConnection());
        TaskDAO actual = factory.createTaskDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createAnswerDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        AnswerDAO expected = new AnswerDAOMySql(sqlConnection.getConnection());
        AnswerDAO actual = factory.createAnswerDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createStatsDAO() {
        when(sqlConnection.getConnection()).thenReturn(connection);

        StatsDAO expected = new StatsDAOMySql(sqlConnection.getConnection());
        StatsDAO actual = factory.createStatsDAO(sqlConnection);

        assertEquals(expected, actual);
    }

    @Test
    public void createConnection() {
    }
}