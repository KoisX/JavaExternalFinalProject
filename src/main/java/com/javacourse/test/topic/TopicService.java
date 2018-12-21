package com.javacourse.test.topic;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.DAOFactory;
import com.javacourse.shared.MySqlDAOFactory;
import com.javacourse.shared.SqlConnection;
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
        try(SqlConnection connection = new SqlConnection()){
            TopicDAO topicDao = factory.createTopicDAO(connection);
            return topicDao.findAll();
        }
    }
}
