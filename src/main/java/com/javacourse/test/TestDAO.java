package com.javacourse.test;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

import java.util.List;

public interface TestDAO extends AbstractDAO<Integer, Test> {
    List<Test> findByTopicId(String id) throws UnsuccessfulQueryException;
}
