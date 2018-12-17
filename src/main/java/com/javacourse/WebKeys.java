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

    /**
     * Key by which error message is saved as a request param
     */
    private static final String ERROR_REQUEST_MESSAGE = "error";

    private static final String LANG_SESSION = "lang";

    private static final String LANG_URL_PARAM = "lang";

    public static String getShouldRedirect() {
        return SHOULD_REDIRECT;
    }

    public static String getErrorRequestMessage() {
        return ERROR_REQUEST_MESSAGE;
    }

    public static String getLangSession() {
        return LANG_SESSION;
    }

    public static String getLangUrlParam() {
        return LANG_URL_PARAM;
    }
}
