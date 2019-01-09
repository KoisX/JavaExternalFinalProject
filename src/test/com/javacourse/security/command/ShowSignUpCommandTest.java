package com.javacourse.security.command;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowSignUpCommandTest {
    private Command showSignUpCommand = new ShowSignUpCommand();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    public void execute() {
        WebPage expected = new WebPage(WebPage.WebPageBase.SIGN_UP_PAGE);
        WebPage actual = showSignUpCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}