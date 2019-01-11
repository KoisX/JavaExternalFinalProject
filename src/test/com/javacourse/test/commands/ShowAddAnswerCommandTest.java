package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAddAnswerCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private ShowAddAnswerCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ShowAddAnswerCommand();
    }

    @Test
    public void execute() {
        when(request.getParameter(anyString())).thenReturn("1");

        WebPage actual = command.execute(request, response);
        WebPage expected = new WebPage(WebPage.WebPageBase.ANSWER_ADMIN_ADD_PAGE);

        assertEquals(expected, actual);
    }
}