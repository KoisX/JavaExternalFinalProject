package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
import com.javacourse.test.answer.AnswerService;
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
public class DeleteAnswerCommandTest {

    @Mock
    HttpServletRequest request;

    private DeleteAnswerCommand command;

    @Before
    public void setUp() throws Exception {
        command = new DeleteAnswerCommand();
    }

    @Test
    public void getPageDependingOnWhetherDeleteIsSuccessful_getsRequestWithCorrectParams_returnsProperWebpage() {
        AnswerService service = mock(AnswerService.class);
        when(service.delete(anyString())).thenReturn(false);
        WebPage expected = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        WebPage actual = command.getPageDependingOnWhetherDeleteIsSuccessful(request, service);
        assertEquals(expected, actual);
    }
}