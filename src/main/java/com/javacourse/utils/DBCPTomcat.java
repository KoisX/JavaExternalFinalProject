package com.javacourse.utils;


import org.apache.log4j.Logger;

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
public class DBCPTomcat {

    private static DataSource dataSource;
    private final static Logger logger;

    //configuring logger
    static {
        logger = LogConfigurator.getLogger(DBCPTomcat.class);
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

    private DBCPTomcat() {
        throw new UnsupportedOperationException("Object instantiation is forbidden");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
