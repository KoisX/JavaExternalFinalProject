package com.javacourse.security.command;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.WebPage;
import com.javacourse.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInCommandTest {

    private SignInCommand command;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new SignInCommand();
    }

    @Test
    public void getPageBasedOnWhetherUserExists_userExists_loggingSuccessful() {
        UserService service = mock(UserService.class );
        when(service.doesUserExist(anyString(), anyString())).thenReturn(true);
        when(request.getSession()).thenReturn(mock(HttpSession.class));

        WebPage expected = new WebPage(WebPage.WebPageBase.INDEX_ACTION).setDispatchType(WebPage.DispatchType.REDIRECT);
        WebPage actual = command.getPageBasedOnWhetherUserExists(request, "password", "login", service);

        assertEquals(expected, actual);
    }

    @Test
    public void getPageBasedOnWhetherUserExists_userNotExists_errorPage() {
        UserService service = mock(UserService.class );
        when(service.doesUserExist(anyString(), anyString())).thenThrow(UnsuccessfulQueryException.class);
        when(request.getSession()).thenReturn(mock(HttpSession.class));

        WebPage expected = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        WebPage actual = command.getPageBasedOnWhetherUserExists(request, "password", "login", service);

        assertEquals(expected, actual);
    }
}