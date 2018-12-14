package com.javacourse.test.task;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.user.UserDAO;
import com.javacourse.utils.DatabaseConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDAO extends AbstractDAO<Integer, Task> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
        new DOMConfigurator().doConfigure(ApplicationResources.getLogConfig(), LogManager.getLoggerRepository());
    }

    /**
     * Created TaskDAO entity
     */
    public TaskDAO(Connection connection){
    }


    //TODO: add implementation
    @Override
    public List<Task> findAll() throws UnsuccessfulQueryException {
        List<Task> items;
        ResultSet rs = null;
        try(    Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                "")){

            rs = statement.executeQuery();
            items = parseToEntityList(rs);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
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
    private List<Task> parseToEntityList(ResultSet rs) {
       // List<Task>
        return null;
    }

    @Override
    public Task findById(Integer id) throws UnsuccessfulQueryException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public boolean create(Task entity) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public Task update(Task entity) throws UnsuccessfulQueryException {
        return null;
    }

}
