package com.javacourse.test.topic;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.shared.dataAccess.SqlConnection;
import com.javacourse.test.TestDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class TopicService {
    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TopicService.class);
    }

    public TopicService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<Topic> findAll() throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            return topicDao.findAll();
        }
    }

    public boolean delete(String id) throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            TestDAO testDAO = factory.createTestDAO(connection);
            if(testDAO.findByTopicId(id).size() >0){
                return false;
            }
            return topicDao.delete(Integer.parseInt(id));
        }
    }

    public boolean create(Topic topic) throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            return topicDao.create(topic);
        }
    }

    public Topic findById(Integer id) throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            return topicDao.findById(id);
        }
    }

    public boolean update(Topic topic) throws SQLException, UnsuccessfulQueryException {
        try(DBConnection connection = factory.createConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            return topicDao.update(topic);
        }
    }
}
