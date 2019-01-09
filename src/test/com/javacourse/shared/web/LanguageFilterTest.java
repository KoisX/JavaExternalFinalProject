package com.javacourse.shared.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LanguageFilterTest {
    LanguageFilter filter;

    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        filter = new LanguageFilter();
    }

    @Test
    public void doFilter_setsLangParamCorrectly() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("ukr");
        when(request.getSession()).thenReturn(session);

        filter.doFilter(request, response, chain);

        verify(request.getSession()).setAttribute("lang", "ukr");
    }
}