package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowStatsDetailsCommandTest {

    private ShowStatsDetailsCommand command;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new ShowStatsDetailsCommand();
    }

    @Test
    public void getWebPageBasedOnWhetherQueryIsSuccessful_worksSuccessfully_returnsCorrectPage() {
        StatsService statsService = mock(StatsService.class);
        WebPage expected = new WebPage(WebPage.WebPageBase.STATS_ADMIN_DETAILS);
        WebPage actual = command.getWebPageBasedOnWhetherQueryIsSuccessful(request,statsService);

        assertEquals(expected, actual);
    }

    @Test
    public void getWebPageBasedOnWhetherQueryIsSuccessful_worksWithError_returnsErrorPage() {
        StatsService statsService = mock(StatsService.class);
        when(statsService.findById(anyString())).thenThrow(UnsuccessfulQueryException.class);
        when(request.getParameter(anyString())).thenReturn("SOME_STRING");
        WebPage expected = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        WebPage actual = command.getWebPageBasedOnWhetherQueryIsSuccessful(request,statsService);

        assertEquals(expected, actual);
    }
}