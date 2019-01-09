package com.javacourse.shared;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WebPageTest {

    WebPage webPage;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void webPage_defaultCtor_setsQuerySrtingToEmptyString() {
        final WebPage.WebPageBase RANDOM_BASE = WebPage.WebPageBase.ERROR_ACTION;
        webPage = new WebPage(RANDOM_BASE);
        assertEquals("", webPage.getQueryString());
    }

    @Test
    public void getPath_withQueryParams_returnsCorrectPath() {
        final WebPage.WebPageBase RANDOM_BASE = WebPage.WebPageBase.ERROR_ACTION;
        final String DEFAULT_QUERY_STRING = "?id=2";
        webPage = new WebPage(RANDOM_BASE);
        webPage.setQueryString(DEFAULT_QUERY_STRING);
        assertEquals(RANDOM_BASE.getPath()+DEFAULT_QUERY_STRING, webPage.getPath());
    }

    @Test
    public void setDispatchType_STANDSTILL_SetsCorrectly() {
        final WebPage.WebPageBase RANDOM_BASE = WebPage.WebPageBase.ERROR_ACTION;
        webPage = new WebPage(RANDOM_BASE);
        webPage.setDispatchType(WebPage.DispatchType.STAND_STILL);

        assertEquals(WebPage.DispatchType.STAND_STILL, webPage.getDispatchType());

    }
}