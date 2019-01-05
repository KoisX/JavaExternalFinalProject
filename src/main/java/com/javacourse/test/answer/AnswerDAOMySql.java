package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class AnswerDAOMySql implements AnswerDAO{

    private Connection connection;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(AnswerDAOMySql.class);
    }

    public AnswerDAOMySql(Connection connection) {
        this.connection = connection;
    }

    private AnswerDAOMySql(){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Answer> findAll() throws UnsuccessfulQueryException {
        return null;
    }

    private List<Answer> parseToEntityList(ResultSet rs) throws SQLException {
        List<Answer> items = new ArrayList<>();
        Answer answer;
        while (rs.next()){
            answer = new Answer();
            answer.setId(rs.getLong("id"));
            answer.setValue(rs.getString("value"));
            answer.setIsCaseSensitive(rs.getBoolean("caseSens"));
            items.add(answer);
        }
        return items;
    }

    @Override
    public Answer findById(Long id) throws UnsuccessfulQueryException {
        Answer answer;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT answer.id as id, answer.value as value, answer.is_case_sensitive as caseSens " +
                    "FROM answer " +
                    "WHERE answer.id = ?; "
        )){

            statement.setLong(1,id);
            resultSet = statement.executeQuery();
            answer = parseSingleEntity(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return answer;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    Answer parseSingleEntity(ResultSet rs) throws SQLException {
        return parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Long id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM answer WHERE id=? ;")){
            statement.setLong(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(Answer entity) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public long createAndGetId(Answer entity) throws UnsuccessfulQueryException {
        long result = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO answer(value, is_case_sensitive) values (?, ?) ; ",
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,entity.getValue());
            statement.setBoolean(2, entity.getIsCaseSensitive());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   result = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return result;
    }

    public List<Answer> findCorrectAnswersByTaskId(long task_id) throws UnsuccessfulQueryException {
        List<Answer> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT answer.id AS id, answer.value AS value, answer.is_case_sensitive as caseSens " +
                            "FROM answer " +
                            "JOIN task_correct_answer a on answer.id = a.answer_id " +
                            "where a.task_id = ?")){

            statement.setLong(1, task_id);
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

    public List<Answer> findPossibleAnswersByTaskId(long task_id) throws UnsuccessfulQueryException {
        List<Answer> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT answer.id AS id, answer.value AS value, answer.is_case_sensitive as caseSens " +
                        "FROM answer " +
                        "JOIN task_possible_answer a on answer.id = a.answer_id " +
                        "where a.task_id = ?")){

            statement.setLong(1, task_id);
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
    public boolean setAsPossibleAnswer(long taskId, long answerId) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO task_possible_answer(task_id, answer_id) values (?, ?) ;")){
            statement.setLong(1, taskId);
            statement.setLong(2, answerId);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean setAsCorrectAnswer(long taskId, long answerId) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO task_correct_answer(task_id, answer_id) values (?, ?) ;")){
            statement.setLong(1, taskId);
            statement.setLong(2, answerId);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
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
