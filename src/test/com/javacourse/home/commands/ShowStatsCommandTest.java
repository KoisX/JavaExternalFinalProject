package com.javacourse.home.commands;

import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowStatsCommandTest {

    private ShowStatsCommand command;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new ShowStatsCommand();
    }

    @Test
    public void isPageAccessible_correctPage_returnsTrue() {
        assertTrue(command.isPageAccessible(1,2));
        assertTrue(command.isPageAccessible(555,556));
    }

    @Test
    public void isPageAccessible_wrongPage_returnsFalse() {
        assertFalse(command.isPageAccessible(-1,2));
        assertFalse(command.isPageAccessible(15,12));
    }

    @Test
    public void getNumberOfPages_interactsWithDatabase_getsCorrectNumberOfPages() {
        final int RANDOM_RESULT = 5;
        StatsService service = mock(StatsService.class);
        when(service.getNumberOfPages(anyInt())).thenReturn(RANDOM_RESULT);

        assertSame(RANDOM_RESULT, command.getNumberOfPages(service));
    }

    @Test
    public void getStats_interactsWithDatabase_getsCorrectStats() {
        final List<Stats> RANDOM_RESULT = new ArrayList<>(Arrays.asList(new Stats()));
        final int CURRENT_PAGE = 2;
        StatsService service = mock(StatsService.class);
        when(service.findAllWithPagination(anyInt(), anyInt())).thenReturn(RANDOM_RESULT);

        assertSame(RANDOM_RESULT, command.getStats(service, CURRENT_PAGE));
    }
}