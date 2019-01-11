package com.javacourse.home.commands;

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
public class ShowAboutCommandTest {

    Command showAboutCommand = new ShowAboutCommand();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;


    @Test
    public void execute_getsCorrectInputData_returnsProperWebpage() {
        WebPage expected = new WebPage(WebPage.WebPageBase.ABOUT_PAGE);
        WebPage actual = showAboutCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}