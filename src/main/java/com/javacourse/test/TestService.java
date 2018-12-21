package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.DAOFactory;
import com.javacourse.shared.MySqlDAOFactory;
import com.javacourse.shared.SqlConnection;
import com.javacourse.test.topic.TopicDAO;
import com.javacourse.test.topic.TopicService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

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
        try(SqlConnection connection = new SqlConnection()){
            TestDAO testDao = factory.createTestDAO(connection);
            return testDao.findByTopicId(id);
        }
    }
}
