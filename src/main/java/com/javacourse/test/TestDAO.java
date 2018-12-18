package com.javacourse.test;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.topic.Topic;
import com.javacourse.user.UserDAO;
import com.javacourse.utils.DatabaseConnectionManager;
import com.javacourse.utils.DatabaseConnectionPoolResource;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an AbstractDAO implementation class,
 * which provides an easy way of interacting with database
 */
public class TestDAO extends AbstractDAO<Integer, Test> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TestDAO.class);
    }

    /**
     * Created TestDAO entity
     */
    public TestDAO(){
    }

    @Override
    public List<Test> findAll() throws UnsuccessfulQueryException {
        List<Test> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT test.id, test.description, t.id, t.name, test.header  " +
                    "FROM test " +
                    "JOIN topic t on test.topic_id = t.id " +
                    "ORDER BY t.name, test.id;")){

            rs = statement.executeQuery();
            items = parseToEntityList(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
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
    List<Test> parseToEntityList(ResultSet rs) throws SQLException {
        List<Test> items = new LinkedList<>();
        Test test;
        Topic topic;
        while (rs.next()){
            test = new Test();
            test.setId(rs.getLong(1));
            test.setDescription(rs.getString(2));

            topic = new Topic();
            topic.setId(rs.getLong(3));
            topic.setName(rs.getString(4));

            test.setHeader(rs.getString(5));
            test.setTopic(topic);
            items.add(test);
        }
        return items;
    }

    @Override
    public Test findById(Integer id) throws UnsuccessfulQueryException {
        Test test;
        ResultSet rs = null;
        try(    Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                "SELECT test.id, test.description, t.id, t.name , test.header " +
                        "FROM test " +
                        "JOIN topic t on test.topic_id = t.id " +
                        "WHERE test.id = ? ;")){

            statement.setLong(1, id);
            rs = statement.executeQuery();
            test = parseSingleEntity(rs);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return test;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    Test parseSingleEntity(ResultSet rs) throws  SQLException{
        return  parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(    Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM test WHERE id=? ;")){

            statement.setInt(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(Test entity) throws UnsuccessfulQueryException {
        int changes = 0;
        try(    Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO test(topic_id, description)" +
                    " VALUES (?,?);")){

            statement.setLong(1,entity.getTopic().getId());
            statement.setString(2,entity.getDescription());
            changes = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    /**
     * Finds all tests for a specific topic
     * @param id topic id
     * @return
     * @throws UnsuccessfulQueryException
     */
    public List<Test> findByTopicId(String id) throws UnsuccessfulQueryException {
        List<Test> items;
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT test.id, test.description, t.id, t.name, test.header " +
                        "FROM test " +
                        "JOIN topic t on test.topic_id = t.id " +
                        "WHERE test.topic_id = ? ")){

            statement.setString(1, id);
            rs = statement.executeQuery();
            items = parseToEntityList(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    //TODO: implement it
    @Override
    public Test update(Test entity) throws UnsuccessfulQueryException {
        throw new UnsupportedOperationException();
    }

}
