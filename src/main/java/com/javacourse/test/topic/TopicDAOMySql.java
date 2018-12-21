package com.javacourse.test.topic;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDAOMySql implements AbstractDAO<Integer, Topic> {

    private final static Logger logger;
    private Connection connection;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(TopicDAOMySql.class);
    }

    /**
     * Created TopicDAOMySql entity
     */
    public TopicDAOMySql(Connection connection) {
        this.connection = connection;
    }

    private TopicDAOMySql() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Topic> findAll() throws UnsuccessfulQueryException {
        List<Topic> items;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT topic.id as id, topic.name as name FROM topic ORDER BY topic.name ;"
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

    List<Topic> parseToEntityList(ResultSet rs) throws SQLException {
        List<Topic> items = new ArrayList<>();
        Topic topic;
        while (rs.next()){
            topic = new Topic();
            topic.setId(rs.getLong("id"));
            topic.setName(rs.getString("name"));
            items.add(topic);
        }
        return items;
    }

    @Override
    public Topic findById(Integer id) throws UnsuccessfulQueryException {
        Topic topic;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT topic.id as id, topic.name as name FROM topic WHERE topic.id = ?;"
        )){

            statement.setLong(1,id);
            resultSet = statement.executeQuery();
            topic = parseSingleEntity(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        } finally {
           closeResultSet(resultSet);
        }
        return topic;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    Topic parseSingleEntity(ResultSet rs) throws SQLException {
        return parseToEntityList(rs).get(0);
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM topic WHERE id=? ;")){
            statement.setInt(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UnsuccessfulQueryException();
        }
        return changes>0;
    }

    @Override
    public boolean create(Topic entity) throws UnsuccessfulQueryException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO topic(name) values (?) ;")){
            statement.setString(1,entity.getName());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
