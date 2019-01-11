package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.TestService;
import com.javacourse.test.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowTestDetailsCommandTest {

    ShowTestDetailsCommand command;

    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new ShowTestDetailsCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void setTasksAttribute_unsuccessfulDbInteraction_returnsFalse() {
        TaskService taskService = mock(TaskService.class);
        TestService testService = mock(TestService.class);
        when(taskService.findTasksByTestId(anyString())).thenThrow(UnsuccessfulQueryException.class);

        assertFalse(command.setTasksAttribute(request, testService, taskService));
    }

    @Test
    public void setTasksAttribute_successfulDbInteraction_returnsTrue() {
        TaskService taskService = mock(TaskService.class);
        TestService testService = mock(TestService.class);
        when(request.getParameter(anyString())).thenReturn("param");
        assertTrue(command.setTasksAttribute(request, testService, taskService));
    }

    @Test
    public void setTasksAttribute_paramIsNull_returnsFalse() {
        TaskService taskService = mock(TaskService.class);
        TestService testService = mock(TestService.class);
        when(request.getParameter(anyString())).thenReturn(null);
        assertFalse(command.setTasksAttribute(request, testService, taskService));
    }
}