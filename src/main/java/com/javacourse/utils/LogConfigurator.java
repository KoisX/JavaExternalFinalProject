package com.javacourse.utils;
import com.javacourse.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Utility class for configuring log4j logger
 */
public class LogConfigurator {
    /**
     * Configures the path to the log4j configuration file
     * and returns a logger for the current class.
     * This method is prefered to its overloaded method, but it can only be used from servlet
     * @param contextPath path to the application context
     * @param clazz name of the class, for which the logger is configured
     * @return log4j logger
     */
    public static Logger getLogger(String contextPath, Class clazz){
        String realPath = contextPath;
        String fileSep = System.getProperty("file.separator");
        if (realPath != null && (!realPath.endsWith(fileSep)))
            realPath = realPath + fileSep;
        PropertyConfigurator.configure(realPath + "WEB-INF/classes/log4j.xml");
        return Logger.getLogger(clazz);
    }

    public static Logger getLogger(Class clazz){
        PropertyConfigurator.configure(ApplicationResources.getLogConfigFile());
        return Logger.getLogger(clazz);
    }
}
