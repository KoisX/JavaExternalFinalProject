package com.javacourse.test.commands;

import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckExamCommandTest {

    CheckExamCommand command;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new CheckExamCommand();
    }

    @Test
    public void getMaxScoreFromDb_normalMethodExecution_returnsDbData() {
        TaskService service = mock(TaskService.class);
        final int SCORE = 1;
        when(service.getMaximalScoreByTestId(anyString())).thenReturn(SCORE);

        int expected = SCORE;
        int actual = service.getMaximalScoreByTestId("id");
        assertEquals(expected, actual);
    }

    @Test
    public void getTasksFromDb_normalMethodExecution_returnsDbData() {
        TaskService service = mock(TaskService.class);
        final List<Task> TASKS = new LinkedList<>();
        when(service.findTasksByTestId(anyString())).thenReturn(TASKS);

        List<Task> expected = TASKS;
        List<Task> actual = service.findTasksByTestId("id");
        assertEquals(expected, actual);
    }

}