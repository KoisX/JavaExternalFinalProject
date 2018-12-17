package com.javacourse;

/**
 * Class, which provides simple API for accessing
 * key values for WEB classes of this project
 */
public class WebKeys {
    /**
     * If the request attribute by this key is noy null,
     * then we should redirect, rather than forward
     */
    private static final String SHOULD_REDIRECT = "redirect";

    public static String getShouldRedirect() {
        return SHOULD_REDIRECT;
    }
}
