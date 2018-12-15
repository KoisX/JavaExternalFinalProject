package com.javacourse;

/**ApplicationResources provides a simple API
 * for retrieving constants and other
 * preconfigured values */
public class ApplicationResources {
    /**
     * Path to the log4j configuration file
     */
    private final static String LOG_CONFIG = "src/main/resources/log4j.xml";

    /**
     * Path to the error page
     */
    private final static String ERROR_PAGE = "/Error";

    public static String getLogConfig(){
        return LOG_CONFIG;
    }

    public static String getErrorPage(String contextPath){
        return contextPath + ERROR_PAGE;
    }
}
