package com.javacourse.user;

import com.javacourse.Constants;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.role.Role;
import com.javacourse.role.RoleFactory;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.utils.DatabaseConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<Integer, User> {

    private final static Logger logger;
    private RoleFactory roleFactory;

    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
        new DOMConfigurator().doConfigure(Constants.LOG_CONFIG, LogManager.getLoggerRepository());
    }

    public UserDAO(Connection connection, RoleFactory roleFactory) {
        super(connection);
        this.roleFactory = roleFactory;
    }

    @Override
    public List<User> findAll() {
        List<User> items;
        try(Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT user.id, user.name, user.surname, user.salt, user.email, role.id, role.name, user.password " +
                        "FROM user_account AS user " +
                        "JOIN role ON user.role_id = role.id " +
                        "ORDER BY surname, name ASC "
            )){

            ResultSet rs = statement.executeQuery();
            items = parseToEntityList(rs);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return items;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    /**
     * Helper-method which incapsulates getting a list of
     * entities from a ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    List<User> parseToEntityList(ResultSet rs) throws SQLException {
        List<User> items = new LinkedList<>();
        User user;
        Role role;
        while(rs.next()){
            user = new User();
            user.setId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setSalt(rs.getString(4));
            user.setEmail(rs.getString(5));

            role = roleFactory.createRole(rs.getString(6), rs.getLong(7));

            user.setRole(role);
            user.setPassword(rs.getString(8));
            items.add(user);
        }
        return items;
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
