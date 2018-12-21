package com.javacourse.stats;

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

public class StatsService {

    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TopicService.class);
    }

    public StatsService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException, SQLException {
        try(SqlConnection connection = new SqlConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.findAllWithPagination(offset, recordsPerPage);
        }
    }

    public int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException, SQLException {
        try(SqlConnection connection = new SqlConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.getNumberOfPages(recordsPerPage);
        }
    }
}
