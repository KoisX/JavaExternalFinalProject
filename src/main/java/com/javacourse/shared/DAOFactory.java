package com.javacourse.shared;

import com.javacourse.user.UserDAO;
import com.javacourse.user.role.RoleDAO;

public interface DAOFactory {
    UserDAO createUserDAO(SqlConnection connection);
    RoleDAO createRoleDAO(SqlConnection connection);
}
