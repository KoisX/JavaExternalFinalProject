package com.javacourse.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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

    public UriMarshallerTest(String uExpected, String uActual) {
        this.uInput = uExpected;
        this.uExpected = uActual;
    }

    @Test
    public void getAction_correctInput_returnsActionString() {
        marshaller = new UriMarshaller(uInput);
        assertEquals(uExpected, marshaller.getAction());
    }

    @Test
    public void parseUri() {
    }
}