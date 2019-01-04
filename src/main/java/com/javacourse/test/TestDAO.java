package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.AbstractDAO;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface TestDAO extends AbstractDAO<Integer, Test> {
    List<Test> findByTopicId(String id) throws UnsuccessfulQueryException;
    boolean delete(String id) throws UnsuccessfulQueryException;
    boolean changeTestStatus(boolean isPublic, long id) throws  UnsuccessfulQueryException;
}
