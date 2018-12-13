package com.javacourse.user;

import com.javacourse.Constants;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleFactory;
import com.javacourse.shared.AbstractDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an AbstractDAO implementation class,
 * which provides an easy way of interacting with database
 */
public class UserDAO extends AbstractDAO<Integer, User> {

    private final static Logger logger;
    private RoleFactory roleFactory;

    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
        new DOMConfigurator().doConfigure(Constants.LOG_CONFIG, LogManager.getLoggerRepository());
    }

    /**
     * Creates UserDAO entity
     * @param connection SQL connection to the desired database
     * @param roleFactory RoleFactory entity, which helps to create
     *                    role entity of enumerable type
     */
    public UserDAO(Connection connection, RoleFactory roleFactory) {
        super(connection);
        this.roleFactory = roleFactory;
    }

    @Override
    public List<User> findAll() throws UnsuccessfulQueryException {
        List<User> items;
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT user.id, user.name, user.surname, user.salt, user.email, role.id, role.name, user.password " +
                        "FROM user_account AS user " +
                        "JOIN role ON user.role_id = role.id " +
                        "ORDER BY surname, name ASC ;"
            )){

            rs = statement.executeQuery();
            items = parseToEntityList(rs);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    /**
     * Helper-method which encapsulates getting a list of
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
    public User findById(Integer id) throws UnsuccessfulQueryException {
        User user;
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT user.id, user.name, user.surname, user.salt, user.email, role.id, role.name, user.password " +
                            "FROM user_account AS user " +
                            "JOIN role ON user.role_id = role.id " +
                            "WHERE user.id = ? ;"
            )){

            statement.setLong(1, id);
            rs = statement.executeQuery();
            user = parseSingleEntity(rs);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return user;
    }


    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    User parseSingleEntity(ResultSet rs) throws  SQLException{
        return  parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM user_account WHERE id=? ;")){
            statement.setInt(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(User entity) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user_account(name, surname, salt, email, role_id, password) " +
                        "values (?,?,?,?,?,?);")){

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getSalt());
            statement.setString(4,entity.getEmail());
            statement.setLong(5, entity.getRole().getId());
            statement.setString(6, entity.getPassword());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    //TODO: implement it
    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException("Operation has yet to be implemented");
    }

    @Override
    public void close() {
        try{
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}
