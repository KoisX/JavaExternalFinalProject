package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicDAO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestDAOTest {

    @Mock
    private Connection connection;

    private TestDAO testDAO;

    @Before
    public void setUp() throws Exception {
        testDAO = new TestDAO(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws UnsuccessfulQueryException, SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAO.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic topic = new Topic(1, "topic");
        com.javacourse.test.Test test = new com.javacourse.test.Test(1, topic, "description");
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong(1)).thenReturn(test.getId());
        when(rs.getString(2)).thenReturn(test.getDescription());
        when(rs.getLong(3)).thenReturn(topic.getId());
        when(rs.getString(4)).thenReturn(topic.getName());

        List<com.javacourse.test.Test> expected = new LinkedList<>();
        expected.add(test);
        List<com.javacourse.test.Test> actual = testDAO.parseToEntityList(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findById_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAO.findById(anyInt());
    }

    @Test
    public void parseSingleEntity_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic topic = new Topic(1, "topic");
        com.javacourse.test.Test expected = new com.javacourse.test.Test(1, topic, "description");
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong(1)).thenReturn(expected.getId());
        when(rs.getString(2)).thenReturn(expected.getDescription());
        when(rs.getLong(3)).thenReturn(topic.getId());
        when(rs.getString(4)).thenReturn(topic.getName());

        com.javacourse.test.Test actual = testDAO.parseSingleEntity(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void delete_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAO.delete(anyInt());
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void create_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAO.create(anyObject());
    }

    @Test
    @Ignore
    public void update() {
    }
}