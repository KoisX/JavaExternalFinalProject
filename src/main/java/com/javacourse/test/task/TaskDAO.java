package com.javacourse.test.task;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.utils.DatabaseConnectionPoolResource;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TaskDAO extends AbstractDAO<Integer, Task> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAO.class);
    }

    /**
     * Created TaskDAO entity
     */
    public TaskDAO(){
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
        List<Task> items = new LinkedList<>();
        AnswerDAO answerDAO = new AnswerDAO();
        Task task;
        while (tasksRS.next()){
            task = new Task();
            task.setId(tasksRS.getLong(1));
            task.setTestId(tasksRS.getLong(2));
            task.setQuestion(tasksRS.getString(3));
            task.setCorrectAnswers(answerDAO.findCorrectAnswersByTaskId(task.getId()));
            task.setPossibleAnswers(answerDAO.findPossibleAnswersByTaskId(task.getId()));
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

    @Override
    public Task update(Task entity) throws UnsuccessfulQueryException {
        return null;
    }

    public List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException{
        List<Task> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                        "SELECT task.id, task.test_id, task.question " +
                            "FROM task WHERE test_id = ? ;")){

            statement.setString(1, test_id);
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
