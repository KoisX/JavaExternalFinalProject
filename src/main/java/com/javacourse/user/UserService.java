package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.shared.dataAccess.SqlConnection;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
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

    public boolean doesUserExist(String email, String password) throws UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.doesUserExist(email, password);
        }
    }

    public Role getUserRoleByEmail(String email) throws UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.getUserRoleByEmail(email);
        }
    }

    public boolean create(User user){
        try(DBConnection connection = factory.createConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            RoleDAO roleDAO = factory.createRoleDAO(connection);
            user.getRole().setId(roleDAO.getRoleIdByName(user.getRole().getName()));
            userDao.create(user);
            return true;
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean doesUserWithEmailExist(String email) throws UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.doesUserWithEmailExist(email);
        }
    }

    public long getUserIdByEmail(String email) throws UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            UserDAO userDao = factory.createUserDAO(connection);
            return userDao.getUserIdByEmail(email);
        }
    }

}
