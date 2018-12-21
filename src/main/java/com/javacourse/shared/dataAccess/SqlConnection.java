package com.javacourse.shared.dataAccess;

import com.javacourse.utils.DBConnectionPool;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Wrapper class for sql Connection object.
 * Simplifies performing basic operations with the connection to DB
 */
public class SqlConnection implements Closeable {

    private Connection connection;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SqlConnection.class);
    }

    public SqlConnection() throws SQLException {
        try {
            this.connection = DBConnectionPool.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        if(connection!=null){
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void commit() {
        if (connection!=null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void rollback() {
        if (connection!=null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void close(){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
