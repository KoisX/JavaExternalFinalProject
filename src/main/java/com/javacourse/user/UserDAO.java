package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.user.role.Role;

public interface UserDAO extends AbstractDAO<Integer, User> {
    boolean doesUserExist(String email, String password) throws UnsuccessfulQueryException;
    Role getUserRoleByEmail(String email) throws UnsuccessfulQueryException;
    boolean doesUserWithEmailExist(String email) throws UnsuccessfulQueryException;
}