package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class StatsService {

    private DAOFactory factory;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(StatsService.class);
    }

    public StatsService() {
        this.factory = new MySqlDAOFactory();
    }

    public List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.findAllWithPagination(offset, recordsPerPage);
        }
    }

    public int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.getNumberOfPages(recordsPerPage);
        }
    }

    public Stats findById(String id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.findById(Integer.parseInt(id));
        }
    }

    public boolean delete(String id) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.delete(Integer.parseInt(id));
        }
    }

    public boolean updateScore(Stats stats) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.updateScore(stats);
        }
    }

    public boolean create(Stats entity) throws UnsuccessfulQueryException, SQLException {
        try(DBConnection connection = factory.createConnection()){
            StatsDAO statsDAO = factory.createStatsDAO(connection);
            return statsDAO.create(entity);
        }
    }
}
