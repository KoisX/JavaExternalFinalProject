package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
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
public class DeleteTestCommandTest {

    @Mock
    HttpServletRequest request;

    private DeleteTestCommand command;

    @Before
    public void setUp() throws Exception {
        command = new DeleteTestCommand();
    }

    @Test
    public void getPageDependingOnWhetherDeleteIsSuccessful() {
        TestService service = mock(TestService.class);
        when(service.delete(anyString())).thenReturn(false);
        WebPage expected = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        WebPage actual = command.getPageDependingOnWhetherDeleteIsSuccessful(request, service);
        assertEquals(expected, actual);
    }
}