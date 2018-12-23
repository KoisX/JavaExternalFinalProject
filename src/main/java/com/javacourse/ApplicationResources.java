package com.javacourse;

/**ApplicationResources provides a simple API
 * for retrieving preconfigured path values */
public class ApplicationResources {

    /*---------- Paths to files -------------*/
    /**
     * Path returned by <code>getContextPath</code> method of the servlet
     */
    private final static String CONTEXT_PATH = "C:/Users/kois/Desktop/Final_Project/target/WebExam/";

    /**
     * Relative path to log config file
     */
    private final static String LOG_INNER_LOCATION = "WEB-INF/classes/log4j.xml";

    /**
     * Path to database.properties file
     */
    private final static String DB_PROPERTIES_PATH = "C:\\Users\\kois\\Desktop\\Final_Project\\src\\main\\resources\\database.properties";

    /*---------- /Paths to files -------------*/
    /*---------- Predefined constants -------------*/

    private final static String DEFAULT_LANG = "en";

    /*---------- /Predefined constants -------------*/

    public static String getContextPath() {
        return CONTEXT_PATH;
    }

    public static String getLogConfigFile() {
        return CONTEXT_PATH + LOG_INNER_LOCATION;
    }

    public static String getDbPropertiesPath() {
        return DB_PROPERTIES_PATH;
    }

    public static String getDefaultLang() {
        return DEFAULT_LANG;
    }

}
