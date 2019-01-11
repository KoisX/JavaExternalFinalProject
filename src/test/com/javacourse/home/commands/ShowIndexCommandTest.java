package com.javacourse.home.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ShowIndexCommandTest {

    Command showIndexCommand = new ShowIndexCommand();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;


    @Test
    public void execute_getsCorrectInputData_returnsProperWebpage() {
        WebPage expected = new WebPage(WebPage.WebPageBase.INDEX_PAGE);
        WebPage actual = showIndexCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}