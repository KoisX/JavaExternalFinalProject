package com.javacourse.shared;

import org.junit.Test;

import static org.junit.Assert.*;

public class WebPageTest {

    @Test
    public void toStringTest() {
        System.out.println(new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                .setQueryString("?id="+"1").toString());
    }
}