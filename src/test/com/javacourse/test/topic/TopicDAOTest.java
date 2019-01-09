package com.javacourse.test.topic;

import com.javacourse.exceptions.UnsuccessfulQueryException;
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
public class TopicDAOTest {

    @Mock
    private Connection connection;

    private TopicDAOMySql topicDAOMySql;

    @Before
    public void setUp() throws Exception {
        topicDAOMySql = new TopicDAOMySql(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        topicDAOMySql.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic topic = new Topic(1, "topic");
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(topic.getId());
        when(rs.getString("name")).thenReturn(topic.getName());

        List<Topic> expected = new LinkedList<>();
        expected.add(topic);
        List<Topic> actual = topicDAOMySql.parseToEntityList(rs);

        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findById_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        topicDAOMySql.findById(anyInt());
    }

    @Test
    public void parseSingleEntity_getsEntity_returnsCorrectEntity() throws SQLException {
        Topic expected = new Topic(1, "topic");
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(expected.getId());
        when(rs.getString("name")).thenReturn(expected.getName());

        Topic actual = topicDAOMySql.parseSingleEntity(rs);

        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void delete_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        topicDAOMySql.delete(anyInt());
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void create_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        topicDAOMySql.create(anyObject());
    }

}