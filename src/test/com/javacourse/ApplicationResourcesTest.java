package com.javacourse;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationResourcesTest {

    @Test
    public void getContextPath() {
        assertEquals(ApplicationResources.getContextPath(), "C:/Users/kois/Desktop/Final_Project/target/WebExam/");
    }

    @Test
    public void getLogConfigFile() {
        assertEquals(ApplicationResources.getLogConfigFile(), "C:/Users/kois/Desktop/Final_Project/target/WebExam/"+"WEB-INF/classes/log4j.xml");
    }

    @Test
    public void getDbPropertiesPath() {
        assertEquals(ApplicationResources.getDbPropertiesPath(), "C:\\Users\\kois\\Desktop\\Final_Project\\src\\main\\resources\\database.properties");
    }

    @Test
    public void getDefaultLang() {
        assertEquals(ApplicationResources.getDefaultLang(), "en");
    }

    @Test
    public void getErrorBundle() {
        assertEquals(ApplicationResources.getErrorBundle(), "error_message");
    }

    @Test
    public void getUserEmail() {
        assertEquals(ApplicationResources.getUserEmail(), "login");
    }
}