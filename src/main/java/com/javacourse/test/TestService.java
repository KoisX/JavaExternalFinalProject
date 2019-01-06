package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.shared.dataAccess.SqlConnection;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerDAO;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.test.task.TaskService;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.test.topic.TopicService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class TestService {
    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TopicService.class);
    }

    public TestService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<Test> findByTopicId(String id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.findByTopicId(id);
        }
    }

    public boolean delete(String id) throws UnsuccessfulQueryException, SQLException{
        try(DBConnection connection = factory.createConnection()){
            connection.setAutoCommit(false);
            TestDAO testDao = factory.createTestDAO(connection);
            TaskDAO taskDAO = factory.createTaskDAO(connection);
            AnswerDAO answerDAO = factory.createAnswerDAO(connection);
            try{
                List<Task> tasks = taskDAO.findTasksByTestId(id);
                for(Task task: tasks){
                    List<Answer> dependentAnswers = answerDAO.findPossibleAnswersByTaskId(task.getId());
                    for(Answer answer: dependentAnswers){
                        answerDAO.delete(answer.getId());
                    }
                }
                testDao.delete(id);
                //tasks and rows from other tables will be deleted with cascade deletion
                connection.commit();
            }catch (UnsuccessfulQueryException e){
                logger.error(e.getMessage());
                connection.rollback();
                return false;
            }

            return true;
        }
    }

    public Test findById(String id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.findById(Integer.parseInt(id));
        }
    }

    public boolean create(Test entity) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.create(entity);
        }
    }

    public boolean makeTestPrivate(Test entity) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.changeTestStatus(false, entity.getId());
        }
    }

    /**
     * According to our business-logic,
     * we can make test public in case next conditions are true:
     * - it has at least 1 task
     * - ALL tasks have at least one correct answer
     * @param entity test
     * @return change status
     * @throws UnsuccessfulQueryException
     * @throws SQLException
     */
    public boolean makeTestPublic(Test entity) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            TaskService taskService = new TaskService();

            List<Task> tasks = taskService.findTasksByTestId(String.valueOf(entity.getId()));
            if(hasAtLeastOneTask(tasks) && doesEachTaskHaveAtLeastOneCorrectAnswer(tasks)){
                testDao.changeTestStatus(true, entity.getId());
            }
            return false;
        }
    }

    boolean hasAtLeastOneTask(List<Task> tasks){
        return tasks.size()>0;
    }

    boolean doesEachTaskHaveAtLeastOneCorrectAnswer(List<Task> tasks){
        return tasks.stream().noneMatch(element -> element.getCorrectAnswers().size() == 0);
    }

    public boolean updateHeader(String header, long id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.updateHeader(header, id);
        }
    }
}
