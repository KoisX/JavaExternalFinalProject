package com.javacourse.shared;

import java.sql.Connection;
import java.util.List;

/**
 * Basic DAO class which is extended by concrete DAO classes
 * @param <K> represents key of the database table
 * @param <T> represents entity type
 */
public abstract class AbstractDAO<K, T extends Entity> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll();
    public abstract T findById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
    //TODO: maybe add close methods for connection, statement and ResultSet
}
