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
    private List<Task> parseToEntityList(ResultSet tasksRS) throws SQLException{
        List<Task> items = new ArrayList<>();
        Task task;
        while (tasksRS.next()){
            task = new Task();
            task.setId(tasksRS.getLong("id"));
            task.setTestId(tasksRS.getLong("testId"));
            task.setQuestion(tasksRS.getString("question"));
            task.setPrice(tasksRS.getInt("price"));
            items.add(task);
        }
        return items;
    }

    @Override
    public Task findById(Integer id) throws UnsuccessfulQueryException {
        Task task;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT task.id as id, task.test_id as testId, task.question as question, task.price as price " +
                    "FROM task "+
                    "WHERE  task.id = ? ;"
        )){

            statement.setLong(1,id);
            resultSet = statement.executeQuery();
            task = parseSingleEntity(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return task;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    Task parseSingleEntity(ResultSet rs) throws SQLException {
        return parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM task WHERE id=? ;")){
            statement.setLong(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean update(Task task) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE task " +
                        "SET task.question = ?, " +
                        "task.price = ? " +
                        "WHERE task.id = ? ;")){
            statement.setString(1,task.getQuestion());
            statement.setLong(2,task.getPrice());
            statement.setLong(3,task.getId());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(Task entity) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO task(test_id, question, price) VALUES(?,?,?);")){
            statement.setLong(1,entity.getTestId());
            statement.setString(2,entity.getQuestion());
            statement.setLong(3, entity.getPrice());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    public List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException{
        List<Task> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                        "SELECT task.id as id, task.test_id as testId, task.question as question, task.price as price " +
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

    @Override
    public int getMaximalScoreByTestId(String test_id) throws UnsuccessfulQueryException {
        int score = 0;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT SUM(task.price) as num " +
                    "FROM task WHERE task.test_id = ?; ")){

            statement.setString(1, test_id);
            resultSet = statement.executeQuery();
            resultSet.next();
            score = resultSet.getInt("num");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return score;
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
