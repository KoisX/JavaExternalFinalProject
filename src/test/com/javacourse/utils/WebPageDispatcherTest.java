package com.javacourse.utils;

import com.javacourse.shared.WebPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebPageDispatcherTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    WebPage webPage;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void dispatch() throws ServletException, IOException {
        final String RANDOM_PATH = "123";

        when(webPage.getDispatchType()).thenReturn(WebPage.DispatchType.FORWARD);
        when(webPage.getPath()).thenReturn(RANDOM_PATH);
        when(request.getContextPath()).thenReturn("");

        WebPageDispatcher webPageDispatcher = new WebPageDispatcher(request, response, webPage);
        webPageDispatcher.dispatch();

        verify(request, times(1)).getRequestDispatcher(anyString());
    }
}