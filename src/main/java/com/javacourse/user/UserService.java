package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.command.SignUpCommand;
import com.javacourse.shared.DAOFactory;
import com.javacourse.shared.MySqlDAOFactory;
import com.javacourse.shared.SqlConnection;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class UserService{
    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserService.class);
    }

    public UserService() {
        this.factory = new MySqlDAOFactory();
    }

    public boolean doesUserExist(String email, String password) throws SQLException, UnsuccessfulQueryException {
        try(SqlConnection connection = new SqlConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.doesUserExist(email, password);
        }
    }

    public Role getUserRoleByEmail(String email) throws SQLException, UnsuccessfulQueryException {
        try(SqlConnection connection = new SqlConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.getUserRoleByEmail(email);
        }
    }

    public boolean create(User user){
        logger.debug("in create");
        try(SqlConnection connection = new SqlConnection()){
            logger.debug("in create try");
            UserDAO userDao = factory.createUserDAO(connection);
            logger.debug("after userdao");
            RoleDAO roleDAO = factory.createRoleDAO(connection);
            logger.debug("after roledao");
            user.getRole().setId(roleDAO.getRoleIdByName(user.getRole().getName()));
            logger.debug("before create");
            userDao.create(user);
            return true;
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean doesUserWithEmailExist(String email) throws UnsuccessfulQueryException, SQLException {
        try(SqlConnection connection = new SqlConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.doesUserWithEmailExist(email);
        }
    }

}
