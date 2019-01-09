package com.javacourse.security;

import com.javacourse.exceptions.UnexistingUrlException;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationCommandFactoryTest {

    AuthorizationCommandFactory authorizationCommandFactory;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;


    @Before
    public void setUp() throws Exception {
        authorizationCommandFactory = new AuthorizationCommandFactory(request, response);
    }

    @Test(expected = UnexistingUrlException.class)
    public void defineCommand_unexistingCommand_throwException() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Home/Bla-bla-bla");

        authorizationCommandFactory.defineCommand();
    }

    @Test
    public void defineCommand_realUrl_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Login/Logout");

        Command expected = LoginCommandEnum.LOGOUT.getCommand();
        Command actual = authorizationCommandFactory.defineCommand();

        assertEquals(expected, actual);
    }

    @Test
    public void defineCommand_realUrlPostMethod_returnsProperCommand() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/Login/SignUp");
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("command")).thenReturn("delete");

        Command expected = LoginCommandEnum.SIGN_UP.getCommand();
        Command actual = authorizationCommandFactory.defineCommand();

        assertEquals(expected, actual);
    }
}