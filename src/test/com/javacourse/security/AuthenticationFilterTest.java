package com.javacourse.security;

import com.javacourse.user.role.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    AuthenticationFilter filter = new AuthenticationFilter();
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String ROLE_PARAM = "role";

    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    public void doFilter_admin_acceptsAdmin() throws IOException, ServletException {
        when(session.getAttribute(LOGIN_PARAM)).thenReturn("random");
        when(session.getAttribute(PASSWORD_PARAM)).thenReturn("random");
        when(session.getAttribute(ROLE_PARAM)).thenReturn(Role.ADMIN);
        when(request.getSession(false)).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    public void isLoggedIn_loggedIn_returnsTrue() {
        when(session.getAttribute(LOGIN_PARAM)).thenReturn(anyString());
        when(session.getAttribute(PASSWORD_PARAM)).thenReturn(anyString());
        assertTrue(filter.isLoggedIn(session));
    }

    @Test
    public void isLoggedIn_notLoggedIn_returnsFalse() {
        when(session.getAttribute(LOGIN_PARAM)).thenReturn(null);
        when(session.getAttribute(PASSWORD_PARAM)).thenReturn(null);
        assertFalse(filter.isLoggedIn(session));
    }

    @Test
    public void isLoggedIn_partiallyLoggedIn_returnsFalse() {
        when(session.getAttribute(LOGIN_PARAM)).thenReturn(anyString());
        when(session.getAttribute(PASSWORD_PARAM)).thenReturn(null);
        assertFalse(filter.isLoggedIn(session));
    }
}