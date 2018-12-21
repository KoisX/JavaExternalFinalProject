package com.javacourse.shared;

import com.javacourse.user.UserDAO;
import com.javacourse.user.UserDAOMySql;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.user.role.RoleDAOMySql;

public class MySqlDAOFactory implements DAOFactory{

    @Override
    public UserDAO createUserDAO(SqlConnection sqlConnection) {
        return new UserDAOMySql(sqlConnection.getConnection());
    }

    @Override
    public RoleDAO createRoleDAO(SqlConnection connection) {
        return new RoleDAOMySql(connection.getConnection());
    }
}
