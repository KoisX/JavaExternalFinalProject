package com.javacourse;

/**ApplicationResources provides a simple API
 * for retrieving preconfigured path values */
public class ApplicationResources {

    /*---------- Pages section --------------*/

    private final static String SIGN_UP_PAGE = "/signup.jsp";

    private final static String INDEX_PAGE = "/index.jsp";

    private final static String LOGIN_PAGE = "/login.jsp";

    private final static String ABOUT_PAGE = "/about.jsp";

    private final static String RULES_PAGE = "/rules.jsp";

    /*---------- /Pages section --------------*/
    /*---------- Actions section --------------*/

    private final static String ERROR_ACTION = "/Error";

    private final static String LOGIN_ACTION = "/Login/SignIn";

    private final static String INDEX_ACTION = "/Home/Index";

    /*---------- /Actions section --------------*/
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

    public static String getErrorPageFull(String contextPath){
        return contextPath + ERROR_ACTION;
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

    public static String getLoginAction() {
        return LOGIN_ACTION;
    }

    public static String getIndexAction() {
        return INDEX_ACTION;
    }

    public static String getErrorAction() {
        return ERROR_ACTION;
    }

    public static String getAboutPage() {
        return ABOUT_PAGE;
    }

    public static String getRulesPage() {
        return RULES_PAGE;
    }
}
