package com.javacourse.utils;


import org.apache.tomcat.jdbc.pool.DataSource;

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

    private static DataSource dataSource = new DataSource();

    static {
        try {
            Context context = (Context) (new InitialContext().lookup("java:comp/env"));
            dataSource = (DataSource) context.lookup("jdbc/student_testing");
        } catch (NamingException e) {
            //TODO log
        }
    }

    private DatabaseConnectionManager() {
        throw new UnsupportedOperationException("Object instantiation is forbidden");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
