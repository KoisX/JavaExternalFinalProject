package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.utils.DatabaseConnectionPoolResource;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends AbstractDAO<Integer, Answer> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAO.class);
    }

    public AnswerDAO() {
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
            answer.setId(rs.getLong(1));
            answer.setValue(rs.getString(2));
            answer.setIsCaseSensitive(rs.getBoolean(3));
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

    @Override
    public Answer update(Answer entity) throws UnsuccessfulQueryException {
        return null;
    }

    public List<Answer> findCorrectAnswersByTaskId(long task_id) throws UnsuccessfulQueryException {
        List<Answer> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT answer.id, answer.value, answer.is_case_sensitive " +
                            "FROM answer " +
                            "JOIN task_correct_answer a on answer.id = a.answer_id " +
                            "where a.task_id = ?")){

            statement.setLong(1, task_id);
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

    public List<Answer> findPossibleAnswersByTaskId(long task_id) throws UnsuccessfulQueryException {
        List<Answer> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT answer.id, answer.value, answer.is_case_sensitive " +
                            "FROM answer " +
                            "JOIN task_possible_answer a on answer.id = a.answer_id " +
                            "where a.task_id = ?")){

            statement.setLong(1, task_id);
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
}
