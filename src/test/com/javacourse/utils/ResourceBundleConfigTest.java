package com.javacourse.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class ResourceBundleConfigTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getErrorResourceBundle() {
        ResourceBundle bundle =  ResourceBundleConfig.getErrorResourceBundle("en");
        assertEquals(bundle.getLocale(), new Locale("en"));
    }

    @Test
    public void getResourceBundle() {
        ResourceBundle bundle =  ResourceBundleConfig.getResourceBundle("en", "error_message");
        assertEquals(bundle.getLocale(), new Locale("en"));
    }
}