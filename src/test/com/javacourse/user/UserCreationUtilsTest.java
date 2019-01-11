package com.javacourse.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserCreationUtilsTest {

    private UserCreationUtils utils;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Before
    public void setUp() throws Exception {
        when(session.getAttribute(anyString())).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        utils = new UserCreationUtils(request);
    }

    @Test
    public void validateInDb_invalidModel_returnsFalse() {
        UserService service = mock(UserService.class);
        when(service.doesUserWithEmailExist(anyString())).thenReturn(true);
        when(session.getAttribute(anyString())).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        User user = new User();
        user.setEmail("email");

        assertFalse(utils.validateInDb(user, request, service));
    }

    @Test
    public void insertUser_getsCorrectUser_returnsTrue() {
        UserService service = mock(UserService.class);
        when(service.create(anyObject())).thenReturn(true);

        assertTrue(utils.insertUser(new User(), service));
    }

    @Test
    public void arePasswordsEqual_getsUserPassword_returnsWhetherTheyAreEqual() {
        assertTrue(utils.arePasswordsEqual("abc", "abc"));
        assertFalse(utils.arePasswordsEqual("ABC", "abc"));
    }
}