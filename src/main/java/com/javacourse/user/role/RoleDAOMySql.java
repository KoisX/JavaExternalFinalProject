package com.javacourse.user.role;

import com.javacourse.exceptions.DangerousQueryDeniedException;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class RoleDAOMySql implements RoleDAO{

    private Connection connection;
    private final static Logger logger;

    public RoleDAOMySql(Connection connection) {
        this.connection = connection;
    }

    private RoleDAOMySql() {
        throw new UnsupportedOperationException();
    }

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(RoleDAOMySql.class);
    }

    @Override
    public List<Role> findAll() throws UnsuccessfulQueryException {
        throw new DangerousQueryDeniedException();
    }

    @Override
    public Role findById(Integer id) throws UnsuccessfulQueryException {
        throw new DangerousQueryDeniedException();
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        throw new DangerousQueryDeniedException();
    }

    @Override
    public boolean create(Role entity) throws UnsuccessfulQueryException {
        throw new DangerousQueryDeniedException();
    }

    public int getRoleIdByName(String role) throws UnsuccessfulQueryException {
        ResultSet resultSet = null;
        int result;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT role.id as id from role where role.name = ?;")){
            statement.setString(1, role);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id");
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
