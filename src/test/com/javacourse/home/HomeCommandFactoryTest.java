package com.javacourse.home;

import com.javacourse.home.commands.HomeCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.test.TestCommandEnum;
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
public class HomeCommandFactoryTest {

    private HomeCommandFactory homeCommandFactory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        homeCommandFactory = new HomeCommandFactory(request, response);
    }

    @Test
    public void defineCommand_wrongUrl_defaultCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Home/Bla-bla-bla");

        Command expected = HomeCommandEnum.SHOW_INDEX.getCommand();
        Command actual = homeCommandFactory.defineCommand();
        assertEquals(expected, actual);
    }

    @Test
    public void defineCommand_correctInput_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Home/Stats");
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("command")).thenReturn("delete");

        Command expected = HomeCommandEnum.DELETE_STATS.getCommand();
        Command actual = homeCommandFactory.defineCommand();

        assertEquals(expected, actual);
    }
}