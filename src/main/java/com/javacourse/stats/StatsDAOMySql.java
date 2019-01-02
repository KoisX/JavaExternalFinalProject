package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
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

/**
 * DAO implementation for the MySQL relational database
 */
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
                    "SELECT a.name as name, a.surname as surname, a.email as email, t.header as header, stats.score as score, stats.id as id,stats.time_passed as time_passed  " +
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
    private List<Stats> parseToEntityList(ResultSet rs) throws SQLException {
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
            stats.setId(rs.getLong("id"));
            stats.setTimePassed(rs.getTimestamp("time_passed"));
            items.add(stats);
        }
        return items;
    }

    @Override
    public Stats findById(Integer id) throws UnsuccessfulQueryException {
        Stats stats;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT a.name as name, a.surname as surname, a.email as email, t.header as header, stats.score as score, stats.id as id ,stats.time_passed as time_passed " +
                        "from stats " +
                        "INNER JOIN user_account a on stats.user_account_id = a.id " +
                        "INNER JOIN test t on stats.test_id = t.id " +
                        "INNER JOIN topic t2 on t.topic_id = t2.id " +
                        "WHERE stats.id = ? ;"
        )){
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            stats = parseSingleEntity(resultSet);

        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        finally {
            closeResultSet(resultSet);
        }
        return stats;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    Stats parseSingleEntity(ResultSet rs) throws  SQLException{
        return  parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM stats WHERE id=? ;")){
            statement.setInt(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(Stats entity) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO stats(user_account_id, test_id, score, time_passed) " +
                        "values (?,?,?,?);")){

            statement.setLong(1, entity.getUser().getId());
            statement.setLong(2, entity.getTest().getId());
            statement.setLong(3,entity.getScore());
            statement.setTimestamp(4, entity.getTimePassed());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    public List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException {
        List<Stats> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.name, a.surname, a.email, t.header, stats.score, stats.id,stats.time_passed as time_passed " +
                            "FROM stats "+
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

    @Override
    public boolean updateScore(Stats stats) throws UnsuccessfulQueryException{
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE stats " +
                    "SET stats.score = ? " +
                    "WHERE stats.id = ? ;")){
            statement.setLong(1,stats.getScore());
            statement.setLong(2,stats.getId());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
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
