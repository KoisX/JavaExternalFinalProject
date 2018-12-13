package com.javacourse.test.topic;

import com.javacourse.Constants;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;
import com.javacourse.user.UserDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TopicDAO extends AbstractDAO<Integer, Topic> {

    private final static Logger logger;

    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
        new DOMConfigurator().doConfigure(Constants.LOG_CONFIG, LogManager.getLoggerRepository());
    }

    /**
     * Created TopicDAO entity
     * @param connection SQL connection to the desired database
     */
    public TopicDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Topic> findAll() throws UnsuccessfulQueryException {
        List<Topic> items;
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT topic.topic_id, topic.name FROM topic ORDER BY topic.name ;"
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

    List<Topic> parseToEntityList(ResultSet rs) throws SQLException {
        List<Topic> items = new LinkedList<>();
        Topic topic;
        while (rs.next()){
            topic = new Topic();
            topic.setId(rs.getLong(1));
            topic.setName(rs.getString(2));
            items.add(topic);
        }
        return items;
    }

    @Override
    public Topic findById(Integer id) throws UnsuccessfulQueryException {
        Topic topic;
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT topic.id, topic.name FROM topic WHERE topic.id = ?;"
        )){

            statement.setLong(1,id);
            rs = statement.executeQuery();
            topic = parseSingleEntity(rs);
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

    @Override
    public Topic update(Topic entity) throws UnsuccessfulQueryException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try{
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
