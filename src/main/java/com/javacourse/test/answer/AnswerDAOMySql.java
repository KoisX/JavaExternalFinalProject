package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOMySql implements AbstractDAO<Integer, Answer> {

    private Connection connection;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAOMySql.class);
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
    public Answer findById(Integer id) throws UnsuccessfulQueryException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public boolean create(Answer entity) throws UnsuccessfulQueryException {
        return false;
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