package com.javacourse.shared;

import com.javacourse.exceptions.UnsuccessfulQueryException;

import java.sql.Connection;
import java.util.List;

/**
 * Basic DAO class which is extended by concrete DAO classes
 * @param <K> represents key of the database table
 * @param <T> represents entity type
 */
public abstract class AbstractDAO<K, T extends Entity> {

    public abstract List<T> findAll() throws UnsuccessfulQueryException;
    public abstract T findById(K id) throws UnsuccessfulQueryException;
    public abstract boolean delete(K id) throws UnsuccessfulQueryException;
    public abstract boolean create(T entity) throws UnsuccessfulQueryException;
    public abstract T update(T entity) throws UnsuccessfulQueryException;
}
