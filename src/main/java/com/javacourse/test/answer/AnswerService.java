package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.DAOFactory;
import com.javacourse.shared.dataAccess.DBConnection;
import com.javacourse.shared.dataAccess.MySqlDAOFactory;
import com.javacourse.stats.StatsService;
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
}
