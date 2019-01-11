package com.javacourse.test.task;

import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.test.answer.AnswerDAOMySql;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    TaskService service;

    TaskDAOMySql taskDao;
    AnswerDAOMySql answerDao;

    @Before
    public void setUp() throws Exception {
        DAOFactory factory = mock(MySqlDAOFactory.class);
        taskDao = mock(TaskDAOMySql.class);
        answerDao = mock(AnswerDAOMySql.class);
        when(factory.createTaskDAO(anyObject())).thenReturn(taskDao);
        when(factory.createAnswerDAO(anyObject())).thenReturn(answerDao);
        service = new TaskService(factory);
    }

    @Test
    public void findTasksByTestId() {
        List<Task> expected = new ArrayList<>();
        expected.add(new Task(1,1,"question", null, null, null));
        when(taskDao.findTasksByTestId(anyString())).thenReturn(expected);
        assertEquals(expected, service.findTasksByTestId("id"));

    }

    @Test
    public void getMaximalScoreByTestId() {
        final int expected = 1;
        when(taskDao.getMaximalScoreByTestId(anyString())).thenReturn(expected);
        assertEquals(expected, service.getMaximalScoreByTestId("id"));
    }

    @Test
    public void findById() {
        Task expected = new Task();
        when(taskDao.findById(anyInt())).thenReturn(expected);
        assertEquals(expected, service.findById("1"));
    }

    @Test
    public void create() {
        when(taskDao.create(anyObject())).thenReturn(false);
        assertFalse(service.create(new Task()));
    }

    @Test
    public void delete() {
        when(taskDao.delete(anyObject())).thenReturn(false);
        assertFalse(service.delete("1"));
    }

    @Test
    public void update() {
        when(taskDao.update(anyObject())).thenReturn(false);
        assertFalse(service.update(new Task()));
    }
}