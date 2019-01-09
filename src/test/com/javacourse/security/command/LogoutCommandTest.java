package com.javacourse.security.command;

import com.javacourse.shared.WebPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    private LogoutCommand logoutCommand;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Before
    public void setUp() throws Exception {
        logoutCommand = new LogoutCommand();
    }

    @Test
    public void execute_executesWell_returnsIndexPage() {
        when(request.getSession()).thenReturn(session);

        WebPage expected = new WebPage(WebPage.WebPageBase.INDEX_ACTION).setDispatchType(WebPage.DispatchType.REDIRECT);
        WebPage actual = logoutCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}