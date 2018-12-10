package com.javacourse.test.task;

import com.javacourse.Constants;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.user.UserDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.util.List;

public class TaskDAO extends AbstractDAO<Integer, Task> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
        new DOMConfigurator().doConfigure(Constants.LOG_CONFIG, LogManager.getLoggerRepository());
    }

    /**
     * Created TaskDAO entity
     * @param connection SQL connection to the desired database
     */
    public TaskDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Task> findAll() throws UnsuccessfulQueryException {
        return null;
    }

    @Override
    public Task findById(Integer id) throws UnsuccessfulQueryException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public boolean create(Task entity) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public Task update(Task entity) throws UnsuccessfulQueryException {
        return null;
    }
}
