package com.javacourse.test.task;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.shared.dataAccess.SqlConnection;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.user.UserService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class TaskService {

    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserService.class);
    }

    public TaskService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException, SQLException {
        try(SqlConnection connection = new SqlConnection()){
            TaskDAO taskDAO = factory.createTaskDAO(connection);
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            List<Task> tasks = taskDAO.findTasksByTestId(test_id);
            for(int i=0; i<tasks.size(); ++i){
                long taskId = tasks.get(i).getId();
                tasks.get(i).setCorrectAnswers(answerDAO.findCorrectAnswersByTaskId(taskId));
                tasks.get(i).setPossibleAnswers(answerDAO.findPossibleAnswersByTaskId(taskId));
            }
            return tasks;
        }
    }
}
