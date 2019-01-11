package com.javacourse.test;

import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.test.answer.AnswerDAOMySql;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.test.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {

    @Mock
    DBConnection connection;

    TestService service;

    TaskDAOMySql taskDao;
    AnswerDAOMySql answerDao;
    TestDAOMySql testDAO;

    @Before
    public void setUp() throws Exception {
        DAOFactory factory = mock(MySqlDAOFactory.class);
        taskDao = mock(TaskDAOMySql.class);
        answerDao = mock(AnswerDAOMySql.class);
        testDAO = mock(TestDAOMySql.class);
        when(factory.createTaskDAO(anyObject())).thenReturn(taskDao);
        when(factory.createAnswerDAO(anyObject())).thenReturn(answerDao);
        when(factory.createTestDAO(anyObject())).thenReturn(testDAO);
        when(factory.createConnection()).thenReturn(connection);
        service = new TestService(factory);
    }

    @Test
    public void findByTopicId_normalMethodExecution_returnsDbData() {
        List<com.javacourse.test.Test> expected = new ArrayList<>();
        expected.add(new com.javacourse.test.Test());

        when(testDAO.findByTopicId(anyString())).thenReturn(expected);
        assertEquals(expected, service.findByTopicId("1"));
    }

    @Test
    public void delete_correctConnection_returnsTrue() {
        assertTrue(service.delete("1"));
    }

    @Test
    public void findById_normalMethodExecution_returnsDbData() {
        com.javacourse.test.Test test = new com.javacourse.test.Test();
        when(testDAO.findById(anyInt())).thenReturn(test);

        assertEquals(test, service.findById("1"));
    }

    @Test
    public void create_normalMethodExecution_returnsDbData() {
        when(testDAO.create(anyObject())).thenReturn(false);
        assertFalse(service.create(new com.javacourse.test.Test()));
    }

    @Test
    public void updateHeader_normalMethodExecution_returnsDbData() {
        when(testDAO.updateHeader(anyObject(), anyLong())).thenReturn(false);
        assertFalse(service.updateHeader("", "1"));
    }

    @Test
    public void updateDescription_normalMethodExecution_returnsDbData() {
        when(testDAO.updateDescription(anyObject(), anyLong())).thenReturn(false);
        assertFalse(service.updateDescription("", "1"));
    }
}