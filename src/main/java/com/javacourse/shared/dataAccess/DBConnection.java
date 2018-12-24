package com.javacourse.shared.dataAccess;

import java.sql.Connection;

/**
 * Basic interface, the implementations of which
 * allow us to switch between databases easily
 */
public interface DBConnection extends AutoCloseable{
    void setAutoCommit(boolean autoCommit);
    void commit();
    void rollback();
    void close();
    Connection getConnection();
}
