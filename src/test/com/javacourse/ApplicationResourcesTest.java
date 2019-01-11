package com.javacourse;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationResourcesTest {

    @Test
    public void getContextPath_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getContextPath(), "C:/Users/kois/Desktop/Final_Project/target/WebExam/");
    }

    @Test
    public void getLogConfigFile_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getLogConfigFile(), "C:/Users/kois/Desktop/Final_Project/target/WebExam/"+"WEB-INF/classes/log4j.xml");
    }

    @Test
    public void getDbPropertiesPath_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getDbPropertiesPath(), "C:\\Users\\kois\\Desktop\\Final_Project\\src\\main\\resources\\database.properties");
    }

    @Test
    public void getDefaultLang_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getDefaultLang(), "en");
    }

    @Test
    public void getErrorBundle_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getErrorBundle(), "error_message");
    }

    @Test
    public void getUserEmail_getProperty_returnsCorrectValue() {
        assertEquals(ApplicationResources.getUserEmail(), "login");
    }
}