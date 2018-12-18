package com.javacourse.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class UriMarshallerTest {
    UriMarshaller marshaller;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "/Home/Index", "Index" }, { "/Home/Index/3", "Index" }, { "/Home", null}
        });
    }

    private String uInput;
    private String uExpected;

    @Mock
    HttpServletRequest request = mock(HttpServletRequest.class);


    public UriMarshallerTest(String uExpected, String uActual) {
        when(request.getRequestURI()).thenReturn(uExpected);
        when(request.getContextPath()).thenReturn("");

        this.uInput = uExpected;
        this.uExpected = uActual;
    }

    @Test
    public void getAction_correctInput_returnsActionString() {
        marshaller = new UriMarshaller(request);
        assertEquals(uExpected, marshaller.getAction());
    }

    @Test
    public void parseUri() {
    }
}