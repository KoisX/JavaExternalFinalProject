package com.javacourse.test.commands;

import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateTaskCommandTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    Task task;

    @Mock
    PrintWriter writer;

    private CreateTaskCommand command;

    @Before
    public void setUp() throws Exception {
        command = new CreateTaskCommand();
    }

    @Test
    public void createTask_getsRequestWithCorrectParams_returnsProperWebpage() throws IOException {
        TaskService service = mock(TaskService.class);
        when(request.getParameter(anyString())).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);
        command.createTask(request, response, task, "ukr", service);
        verify(writer).write(anyString());
    }
}