package com.javacourse;

/**
 * Class, which provides simple API for accessing
 * key values for WEB classes of this project
 */
public class WebKeys {

    /**
     * Key by which error message is saved as a request param
     */
    private static final String ERROR_REQUEST_MESSAGE = "error";

    private static final String LANG_SESSION = "lang";

    private static final String LANG_URL_PARAM = "lang";

    private static final String ROLE_KEY = "role";

    public static String getErrorRequestMessage() {
        return ERROR_REQUEST_MESSAGE;
    }

    public static String getLangSession() {
        return LANG_SESSION;
    }

    public static String getLangUrlParam() {
        return LANG_URL_PARAM;
    }

    public static String getRoleKey() {
        return ROLE_KEY;
    }
}
