package com.javacourse.user;

import com.javacourse.shared.AbstractDAO;

import java.sql.Connection;
import java.util.List;

public class UserDAO extends AbstractDAO<Integer, User> {

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
