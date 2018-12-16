package com.javacourse.user.role;

import com.javacourse.exceptions.DangerousQueryDeniedException;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.user.UserDAO;
import com.javacourse.utils.DatabaseConnectionPoolResource;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAO  extends AbstractDAO<Integer, Role> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserDAO.class);
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

    @Override
    public Role update(Role entity) throws UnsuccessfulQueryException {
        throw new DangerousQueryDeniedException();
    }

    public int getRoleIdByName(String role) throws UnsuccessfulQueryException {
        ResultSet rs = null;
        int result;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT role.id from role where role.name = ?;")){
            statement.setString(1, role);
            rs = statement.executeQuery();
            rs.next();
            result = rs.getInt(1);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return result;
    }
}
