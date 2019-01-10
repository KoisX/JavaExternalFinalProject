package com.javacourse.home.commands;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

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
    public void getPageInfo() {

    }
}