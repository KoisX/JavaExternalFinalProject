package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.user.role.AdminUserRoleFactory;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleFactory;
import com.javacourse.utils.DBConnectionPool;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class UserDAOMySql implements UserDAO {

    private final static Logger logger;
    private RoleFactory roleFactory;
    private Connection connection;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserDAOMySql.class);
    }

    /**
     * Creates UserDAOMySql entity
     * @param roleFactory RoleFactory entity, which helps to create
     *                    role entity of enumerable type
     */
    public UserDAOMySql(Connection connection, RoleFactory roleFactory) {
        this.roleFactory = roleFactory;
        this.connection = connection;
    }

    /**
     * Creates UserDAOMySql entity with default role factory
     */
    public UserDAOMySql(Connection connection) {
        this.roleFactory = new AdminUserRoleFactory();
        this.connection = connection;
    }

    public UserDAOMySql(){
        this.roleFactory = new AdminUserRoleFactory();
    }

    @Override
    public List<User> findAll() throws UnsuccessfulQueryException {
        List<User> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT user.id as id, user.name as name, user.surname as surname, user.email as email, role.id as roleID, role.name as roleName, user.password as password " +
                        "FROM user_account AS user " +
                        "JOIN role ON user.role_id = role.id " +
                        "ORDER BY surname, name ASC ;"
            )){
            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
        }
        return items;
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param resultSet ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    List<User> parseToEntityList(ResultSet resultSet) throws SQLException {
        List<User> items = new ArrayList<>();
        User user;
        Role role;
        while(resultSet.next()){
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            role = roleFactory.createRole(resultSet.getString("roleName"), resultSet.getLong("roleID"));
            user.setRole(role);
            user.setPassword(resultSet.getString("password"));
            items.add(user);
        }
        return items;
    }

    @Override
    public User findById(Integer id) throws UnsuccessfulQueryException {
        User user;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT user.id, user.name, user.surname, user.email, role.id, role.name, user.password " +
                            "FROM user_account AS user " +
                            "JOIN role ON user.role_id = role.id " +
                            "WHERE user.id = ? ;"
            )){
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            user = parseSingleEntity(resultSet);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
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
                "INSERT INTO user_account(name, surname, email, role_id, password) " +
                        "values (?,?,?,?,?);")){

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3,entity.getEmail());
            statement.setLong(4, entity.getRole().getId());
            statement.setString(5, entity.getPassword());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    /**
     * Check if db contains a used identified by a pair of login and password
     * @param email
     * @param password
     * @return true if such user exists, false otherwise
     */
    @Override
    public boolean doesUserExist(String email, String password) throws UnsuccessfulQueryException {
        ResultSet resultSet = null;
        boolean result;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(user.id) AS total FROM user_account AS user " +
                        "WHERE user.email = ? AND user.password = ? ;")){
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("total") > 0;
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    public Role getUserRoleByEmail(String email) throws UnsuccessfulQueryException{
        ResultSet resultSet = null;
        String role;
        Role result;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT role.name as role " +
                        "from user_account inner JOIN role on user_account.role_id = role.id " +
                        "where user_account.email = ?;"
                        )){

            statement.setString(1,email);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = resultSet.getString("role");
            result = roleFactory.createRole(role);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    public boolean doesUserWithEmailExist(String email) throws UnsuccessfulQueryException {
        ResultSet resultSet = null;
        boolean result;
        try(Connection connection = DBConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(user.id) AS total FROM user_account AS user " +
                            "WHERE user.email = ? ;")){
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("total") > 0;
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    /**
     * Service method for closing ResultSet object entity
     * @param resultSet
     */
    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
