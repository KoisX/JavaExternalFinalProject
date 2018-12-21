package com.javacourse.test.topic;

import com.javacourse.shared.AbstractDAO;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface TopicDAO extends AbstractDAO<Integer, Topic> {

}
