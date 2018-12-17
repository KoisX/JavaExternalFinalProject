package com.javacourse;

/**ApplicationResources provides a simple API
 * for retrieving preconfigured path values */
public class ApplicationResources {
    /**
     * Path to the log4j configuration file
     */
    private final static String LOG_CONFIG_FOR_JAVA_CODE = "src/main/resources/log4j.xml";

    /**
     * Path to the error page
     */
    private final static String ERROR_PAGE = "/Error";

    /**
     * Path returned by <code>getContextPath</code> method of the servlet
     */
    private final static String CONTEXT_PATH = "C:/Users/kois/Desktop/Final_Project/target/WebExam/";

    /**
     * Relative path to log config file
     */
    private final static String LOG_INNER_LOCATION = "WEB-INF/classes/log4j.xml";

    private final static String SIGN_UP_PAGE = "/signup.jsp";

    private final static String INDEX_PAGE = "/index.jsp";

    private final static String LOGIN_PAGE = "/login.jsp";

    /**
     * Path to database.properties file
     */
    private final static String DB_PROPERTIES_PATH = "C:\\Users\\kois\\Desktop\\Final_Project\\src\\main\\resources\\database.properties";

    public static String getLogConfigForJavaCode(){
        return LOG_CONFIG_FOR_JAVA_CODE;
    }

    public static String getErrorPage(String contextPath){
        return contextPath + ERROR_PAGE;
    }

    public static String getContextPath() {
        return CONTEXT_PATH;
    }

    public static String getLogInnerLocation() {
        return LOG_INNER_LOCATION;
    }

    public static String getLogConfigFile(){
        return CONTEXT_PATH+LOG_INNER_LOCATION;
    }

    public static String getDbPropertiesPath() {
        return DB_PROPERTIES_PATH;
    }

    public static String getSignUpPage() {
        return SIGN_UP_PAGE;
    }

    public static String getIndexPage() {
        return INDEX_PAGE;
    }

    public static String getLoginPage() {
        return LOGIN_PAGE;
    }
}
