package com.javacourse.user.role;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface RoleDAO extends AbstractDAO<Integer, Role> {
    int getRoleIdByName(String roleName) throws UnsuccessfulQueryException;
}
