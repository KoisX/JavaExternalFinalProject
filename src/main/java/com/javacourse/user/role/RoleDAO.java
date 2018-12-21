package com.javacourse.user.role;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

public interface RoleDAO extends AbstractDAO<Integer, Role> {
    int getRoleIdByName(String roleName) throws UnsuccessfulQueryException;
}
