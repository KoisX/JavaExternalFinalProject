package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.Test;
import com.javacourse.test.task.TaskDAO;
import com.javacourse.user.User;
import com.javacourse.utils.DatabaseConnectionPoolResource;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StatsDAO extends AbstractDAO<Integer, Stats> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAO.class);
    }

    public StatsDAO() {
    }

    @Override
    public List<Stats> findAll() throws UnsuccessfulQueryException {
        List<Stats> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.name, a.surname, a.email, t.header, stats.score from stats " +
                            "INNER JOIN user_account a on stats.user_account_id = a.id " +
                            "INNER JOIN test t on stats.test_id = t.id " +
                            "INNER JOIN topic t2 on t.topic_id = t2.id;"
            )){

            rs = statement.executeQuery();
            items = parseToEntityList(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<Stats> parseToEntityList(ResultSet rs) throws SQLException, UnsuccessfulQueryException {
        List<Stats> items = new LinkedList<>();
        Stats stats;
        User user;
        Test test;
        while (rs.next()){
            stats = new Stats();
            user = new User();
            test = new Test();

            user.setName(rs.getString(1));
            user.setSurname(rs.getString(2));
            user.setEmail(rs.getString(3));
            test.setHeader(rs.getString(4));

            stats.setUser(user);
            stats.setTest(test);
            stats.setScore(rs.getInt(5));
            items.add(stats);
        }
        return items;
    }

    @Override
    public Stats findById(Integer id) throws UnsuccessfulQueryException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public boolean create(Stats entity) throws UnsuccessfulQueryException {
        return false;
    }

    @Override
    public Stats update(Stats entity) throws UnsuccessfulQueryException {
        return null;
    }

    public List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException {
        List<Stats> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.name, a.surname, a.email, t.header, stats.score from stats " +
                            "INNER JOIN user_account a on stats.user_account_id = a.id " +
                            "INNER JOIN test t on stats.test_id = t.id " +
                            "INNER JOIN topic t2 on t.topic_id = t2.id " +
                            "ORDER BY t.header, stats.score LIMIT ?, ?;"
            )){

            statement.setInt(1,offset);
            statement.setInt(2,offset + recordsPerPage);
            rs = statement.executeQuery();
            items = parseToEntityList(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    public int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException {
        int result = 0;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(stats.score) AS num FROM stats;"
            )){

            rs = statement.executeQuery();
            rs.next();
            result = rs.getInt("num");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        if(recordsPerPage>0)
            result = result/recordsPerPage + 1;
        else result = 0;
        return result;
    }

}
