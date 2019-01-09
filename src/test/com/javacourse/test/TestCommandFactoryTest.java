package com.javacourse.test;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.TopicCommandEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCommandFactoryTest {

    TestCommandFactory testCommandFactory;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        testCommandFactory = new TestCommandFactory(request, response);
    }

    @Test
    public void defineCommand_wrongUrl_defaultCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Test/Bla-bla-bla");

        Command expected = TopicCommandEnum.SHOW_TOPICS.getCommand();
        Command actual = testCommandFactory.defineCommand();
        assertEquals(expected, actual);
    }

    @Test
    public void defineCommand_correctInput_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Test/TaskDetails");
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("command")).thenReturn("delete");

        Command expected = TestCommandEnum.DELETE_TASK.getCommand();
        Command actual = testCommandFactory.defineCommand();

        assertEquals(expected, actual);
    }
}