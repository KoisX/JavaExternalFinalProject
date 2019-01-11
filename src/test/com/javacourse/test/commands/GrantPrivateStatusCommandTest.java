package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GrantPrivateStatusCommandTest {

    @Mock
    HttpServletRequest request;

    private GrantPrivateStatusCommand command;

    @Before
    public void setUp() throws Exception {
        command = new GrantPrivateStatusCommand();
    }

    @Test
    public void getPageDependingOnWhetherOperationIsSuccessful() {
        TestService service = mock(TestService.class);
        when(service.makeTestPrivate(anyObject())).thenReturn(true);
        when(request.getParameter(anyString())).thenReturn("1");
        WebPage expected = new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                .setQueryString("?id="+request.getParameter("id"))
                .setDispatchType(WebPage.DispatchType.REDIRECT);
        WebPage actual = command.getPageDependingOnWhetherOperationIsSuccessful(request, service);
        assertEquals(expected, actual);
    }
}