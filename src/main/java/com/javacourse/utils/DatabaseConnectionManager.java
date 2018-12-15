package com.javacourse.utils;


import com.javacourse.ApplicationResources;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Utility class which provides an easy way for
 * using database connections from a connection pool
 */
public class DatabaseConnectionManager {

    private static DataSource dataSource;
    private final static Logger logger = Logger.getLogger(DatabaseConnectionManager.class);

    //configuring logger
    static {
        new DOMConfigurator().doConfigure(ApplicationResources.getLogConfig(), LogManager.getLoggerRepository());
    }

    //configuring database connection properties
    static {
        try {
            Context context = (Context) (new InitialContext().lookup("java:comp/env"));
            dataSource = (DataSource) context.lookup("jdbc/student_testing");
        } catch (NamingException e) {
            logger.error(e.getMessage());
        }
    }

    private DatabaseConnectionManager() {
        throw new UnsupportedOperationException("Object instantiation is forbidden");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
