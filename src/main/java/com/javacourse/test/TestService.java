package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.shared.dataAccess.SqlConnection;
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
}
