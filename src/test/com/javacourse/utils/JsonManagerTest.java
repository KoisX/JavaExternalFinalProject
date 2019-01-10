package com.javacourse.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JsonManagerTest {

    private HttpServletResponse response;

    @Mock
    PrintWriter printWriter;

    @Before
    public void setUp() throws Exception {
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void put() {
        JsonManager manager = new JsonManager(response);

        assertEquals(manager, manager.put("key", "value"));
    }

    @Test
    public void respond() throws IOException {
        JsonManager manager = new JsonManager(response);
        when(response.getWriter()).thenReturn(printWriter);
        manager.respond();

        verify(response, times(2)).getWriter();
    }

    @Test
    public void sendSingleMessage() throws IOException {
        when(response.getWriter()).thenReturn(printWriter);
        JsonManager.sendSingleMessage("", "", response);

        verify(response, times(2)).getWriter();
    }
}