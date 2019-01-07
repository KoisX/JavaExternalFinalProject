package com.javacourse.test.topic;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.AbstractDAO;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface TopicDAO extends AbstractDAO<Integer, Topic> {
    boolean update(Topic topic) throws UnsuccessfulQueryException;
    boolean doesTopicExist(Integer id) throws UnsuccessfulQueryException;
}
