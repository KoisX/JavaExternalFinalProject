package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.topic.Topic;
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
public class TestDAOTestMySql {

    @Mock
    private Connection connection;

    private TestDAOMySql testDAOMySql;

    @Before
    public void setUp() throws Exception {
        testDAOMySql = new TestDAOMySql(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws UnsuccessfulQueryException, SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAOMySql.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic topic = new Topic(1, "topic");
        com.javacourse.test.Test test = new com.javacourse.test.Test(1, topic, "description", "header", false);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(test.getId());
        when(rs.getString("description")).thenReturn(test.getDescription());
        when(rs.getLong("topicId")).thenReturn(topic.getId());
        when(rs.getString("topicName")).thenReturn(topic.getName());
        when(rs.getString("header")).thenReturn(test.getHeader());
        //when(rs.getString("getIsPublic")).thenReturn(test.getIsPublic());
        List<com.javacourse.test.Test> expected = new LinkedList<>();
        expected.add(test);
        List<com.javacourse.test.Test> actual = testDAOMySql.parseToEntityList(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findById_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAOMySql.findById(anyInt());
    }

    @Test
    public void parseSingleEntity_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic topic = new Topic(1, "topic");
        com.javacourse.test.Test expected = new com.javacourse.test.Test(1, topic, "description", "header", false);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(expected.getId());
        when(rs.getString("description")).thenReturn(expected.getDescription());
        when(rs.getLong("topicId")).thenReturn(topic.getId());
        when(rs.getString("topicName")).thenReturn(topic.getName());
        when(rs.getString("header")).thenReturn(expected.getHeader());
        //when(rs.getString("getIsPublic")).thenReturn(expected.getIsPublic());

        com.javacourse.test.Test actual = testDAOMySql.parseSingleEntity(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void delete_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAOMySql.delete(anyInt());
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void create_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        testDAOMySql.create(anyObject());
    }
}