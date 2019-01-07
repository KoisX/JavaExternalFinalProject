package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class AnswerService {
    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(AnswerService.class);
    }

    public AnswerService() {
        this.factory = new MySqlDAOFactory();
    }

    public Answer findById(int id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            return answerDAO.findById((long) id);
        }
    }

    public boolean create(Answer entity, boolean isCorrect, long taskId) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            connection.setAutoCommit(false);
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            try{
                long id = answerDAO.createAndGetId(entity);
                answerDAO.createAsPossibleAnswer(taskId, id);
                if(isCorrect){
                    answerDAO.createAsCorrectAnswer(taskId, id);
                }
                connection.commit();
            }catch (UnsuccessfulQueryException e){
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }

    public boolean delete(Long id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            return answerDAO.delete(id);
        }
    }

    public boolean update(Answer answer, boolean isCorrectAnswer, long taskId) throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            connection.setAutoCommit(false);
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            try{
                answerDAO.update(answer);
                boolean isCorrectFromDb = answerDAO.isCorrect(answer.getId());
                if(isCorrectFromDb != isCorrectAnswer){
                    if(isCorrectAnswer){
                        answerDAO.createAsCorrectAnswer(taskId, answer.getId());
                    }else {
                        answerDAO.deleteAsCorrectAnswer(taskId, answer.getId());
                    }
                }
                connection.commit();
            }catch (UnsuccessfulQueryException e){
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }
            return true;
        }
    }

}
