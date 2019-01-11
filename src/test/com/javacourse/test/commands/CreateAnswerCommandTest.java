package com.javacourse.test.commands;

import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateAnswerCommandTest {

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    Answer answer;

    @Mock
    PrintWriter writer;

    private CreateAnswerCommand command;

    @Before
    public void setUp() throws Exception {
        command = new CreateAnswerCommand();
    }

    @Test
    public void createAnswer_getsRequestWithCorrectParams_returnsProperWebpage() throws IOException {
        AnswerService service = mock(AnswerService.class);
        when(request.getParameter(anyString())).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);
        command.createAnswer(request, response, answer, "ukr", service);
        verify(writer).write(anyString());
    }
}