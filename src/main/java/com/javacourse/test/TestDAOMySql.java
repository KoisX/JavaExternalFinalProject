package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.test.topic.Topic;
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
public class TestDAOMySql implements TestDAO {

    private final static Logger logger;
    private Connection connection;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TestDAOMySql.class);
    }

    /**
     * Created TestDAOMySql entity
     */
    public TestDAOMySql(Connection connection){
        this.connection = connection;
    }

    private TestDAOMySql(){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Test> findAll() throws UnsuccessfulQueryException {
        List<Test> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT test.id as id, test.description as description, t.id as topicId, t.name as topicName, test.header as header " +
                    "FROM test " +
                    "JOIN topic t on test.topic_id = t.id " +
                    "ORDER BY t.name, test.id;")){

            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
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
    List<Test> parseToEntityList(ResultSet rs) throws SQLException {
        List<Test> items = new ArrayList<>();
        Test test;
        Topic topic;
        while (rs.next()){
            test = new Test();
            test.setId(rs.getLong("id"));
            test.setDescription(rs.getString("description"));

            topic = new Topic();
            topic.setId(rs.getLong("topicId"));
            topic.setName(rs.getString("topicName"));

            test.setHeader(rs.getString("header"));
            test.setTopic(topic);
            items.add(test);
        }
        return items;
    }

    @Override
    public Test findById(Integer id) throws UnsuccessfulQueryException {
        Test test;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT test.id as id, test.description as description, t.id as topicId, t.name as topicName, test.header as header " +
                        "FROM test " +
                        "JOIN topic t on test.topic_id = t.id " +
                        "WHERE test.id = ? ;")){

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            test = parseSingleEntity(resultSet);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
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
        try(PreparedStatement statement = connection.prepareStatement(
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
        try(PreparedStatement statement = connection.prepareStatement(
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
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT test.id as id, test.description as description, t.id as topicId, t.name as topicName, test.header as header " +
                        "FROM test " +
                        "JOIN topic t on test.topic_id = t.id " +
                        "WHERE test.topic_id = ? ")){

            statement.setString(1, id);
            resultSet = statement.executeQuery();
            items = parseToEntityList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
            closeResultSet(resultSet);
        }
        return items;
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
