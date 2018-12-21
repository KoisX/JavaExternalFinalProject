package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

import java.util.List;

public interface AnswerDAO extends AbstractDAO<Integer, Answer> {
    List<Answer> findCorrectAnswersByTaskId(long task_id) throws UnsuccessfulQueryException;
    List<Answer> findPossibleAnswersByTaskId(long task_id) throws UnsuccessfulQueryException;
}
