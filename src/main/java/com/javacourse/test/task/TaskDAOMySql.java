package com.javacourse.test.task;

import com.javacourse.exceptions.UnsuccessfulQueryException;
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
public class TaskDAOMySql implements TaskDAO {

    private Connection connection;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAOMySql.class);
    }

    /**
     * Created TaskDAOMySql entity
     */
    public TaskDAOMySql(Connection connection){
        this.connection = connection;
    }

    private TaskDAOMySql(){
        throw new UnsupportedOperationException();
    }

    //TODO: add implementation
    @Override
    public List<Task> findAll() throws UnsuccessfulQueryException {
        return null;
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param tasksRS ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<Task> parseToEntityList(ResultSet tasksRS) throws SQLException, UnsuccessfulQueryException {
        List<Task> items = new ArrayList<>();
        Task task;
        while (tasksRS.next()){
            task = new Task();
            task.setId(tasksRS.getLong("id"));
            task.setTestId(tasksRS.getLong("testId"));
            task.setQuestion(tasksRS.getString("question"));
            items.add(task);
        }
        return items;
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

    public List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException{
        List<Task> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                        "SELECT task.id as id, task.test_id as testId, task.question as question " +
                            "FROM task WHERE test_id = ? ;")){

            statement.setString(1, test_id);
            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return items;
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
