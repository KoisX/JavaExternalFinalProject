package com.javacourse.security.command;

import com.javacourse.user.User;
import com.javacourse.user.role.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpCommandTest {

    SignUpCommand signUpCommand = new SignUpCommand();

    @Mock
    HttpServletRequest request;

    private static final String DEFAULT_PASSWORD = "PASSWORD";
    private static final String DEFAULT_NAME = "NAME";
    private static final String DEFAULT_SURNAME = "SURNAME";
    private static final String DEFAULT_MAIL = "MAIL";
    private static final long DEFAULT_ID = 0;
    private static final Role DEFAULT_ROLE = Role.USER;
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";

    @Before
    public void setUp() throws Exception {
        when(request.getParameter(LOGIN_PARAM)).thenReturn(DEFAULT_MAIL);
        when(request.getParameter(PASSWORD_PARAM)).thenReturn(DEFAULT_PASSWORD);
        when(request.getParameter(NAME_PARAM)).thenReturn(DEFAULT_NAME);
        when(request.getParameter(SURNAME_PARAM)).thenReturn(DEFAULT_SURNAME);
    }

    @Test
    public void constructUser_getsCorrectUser_returnsWellformedWebPage() {
        User expected = new User(DEFAULT_ID, DEFAULT_NAME, DEFAULT_SURNAME, DEFAULT_MAIL, DEFAULT_ROLE, DEFAULT_PASSWORD);
        User actual = signUpCommand.constructUser(request);

        assertEquals(expected, actual);
    }
}