package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.Test;
import com.javacourse.test.task.TaskDAOMySql;
import com.javacourse.user.User;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsDAOMySql implements StatsDAO {

    private final static Logger logger;
    private Connection connection;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TaskDAOMySql.class);
    }

    public StatsDAOMySql(Connection connection) {
        this.connection = connection;
    }

    private StatsDAOMySql() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Stats> findAll() throws UnsuccessfulQueryException {
        List<Stats> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.name as name, a.surname as surname, a.email as email, t.header as header, stats.score as score " +
                        "from stats " +
                        "INNER JOIN user_account a on stats.user_account_id = a.id " +
                        "INNER JOIN test t on stats.test_id = t.id " +
                        "INNER JOIN topic t2 on t.topic_id = t2.id;"
            )){
            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
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
        List<Stats> items = new ArrayList<>();
        Stats stats;
        User user;
        Test test;
        while (rs.next()){
            stats = new Stats();
            user = new User();
            test = new Test();

            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            test.setHeader(rs.getString("header"));

            stats.setUser(user);
            stats.setTest(test);
            stats.setScore(rs.getInt("score"));
            items.add(stats);
        }
        return items;
    }

    @Override
    public Stats findById(Integer id) throws UnsuccessfulQueryException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Stats entity) throws UnsuccessfulQueryException {
        throw new UnsupportedOperationException();
    }

    public List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException {
        List<Stats> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.name, a.surname, a.email, t.header, stats.score from stats " +
                            "INNER JOIN user_account a on stats.user_account_id = a.id " +
                            "INNER JOIN test t on stats.test_id = t.id " +
                            "INNER JOIN topic t2 on t.topic_id = t2.id " +
                            "ORDER BY t.header, stats.score LIMIT ?, ?;"
            )){

            statement.setInt(1,offset);
            statement.setInt(2,offset + recordsPerPage);
            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
        }
        return items;
    }

    public int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException {
        int result = 0;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(stats.score) AS num FROM stats;"
            )){

            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("num");
            if(recordsPerPage>0)
                result = result/recordsPerPage + 1;
            else result = 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    /**
     * Service method for closing ResultSet object entity
     * @param resultSet
     */
    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}
