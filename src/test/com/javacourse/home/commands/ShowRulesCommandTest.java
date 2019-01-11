package com.javacourse.home.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ShowRulesCommandTest {
    Command showRulesCommand = new ShowRulesCommand();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;


    @Test
    public void execute_getsCorrectInputData_returnsProperWebpage() {
        WebPage expected = new WebPage(WebPage.WebPageBase.RULES_PAGE);
        WebPage actual = showRulesCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}