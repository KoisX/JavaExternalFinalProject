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

    Command showIndexCommand = new ShowIndexCommand();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;


    @Test
    public void execute() {
        WebPage expected = new WebPage(WebPage.WebPageBase.INDEX_PAGE);
        WebPage actual = showIndexCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}