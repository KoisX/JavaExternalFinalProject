package com.javacourse.test.topic;

import com.javacourse.security.command.LoginCommandEnum;
import com.javacourse.shared.Command;
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
public class TopicCommandFactoryTest {

    TopicCommandFactory topicCommandFactory;
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;


    @Before
    public void setUp() throws Exception {
        topicCommandFactory = new TopicCommandFactory(request, response);
    }

    @Test
    public void defineCommand_wrongUrl_returnsDefaultCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Topic/Bla-bla-bla");
        Command expected = TopicCommandEnum.SHOW_TOPICS.getCommand();
        Command actual = topicCommandFactory.defineCommand();
        assertEquals(expected, actual);
    }

    @Test
    public void defineCommand_correctInput_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Topic/List");
        when(request.getMethod()).thenReturn("POST");
        Command expected = TopicCommandEnum.DELETE_TOPIC.getCommand();
        Command actual = topicCommandFactory.defineCommand();
        assertEquals(expected, actual);
    }

    @Test
    public void defineCommand_editCommand_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Topic/Edit");
        when(request.getMethod()).thenReturn("POST");

        Command expected = TopicCommandEnum.SHOW_EDIT_PAGE.getCommand();
        Command actual = topicCommandFactory.defineCommand();
        assertEquals(expected, actual);
    }
}