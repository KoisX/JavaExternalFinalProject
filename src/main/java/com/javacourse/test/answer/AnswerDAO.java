package com.javacourse.test.answer;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.AbstractDAO;
import com.javacourse.test.topic.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface AnswerDAO extends AbstractDAO<Long, Answer> {
    List<Answer> findCorrectAnswersByTaskId(long task_id) throws UnsuccessfulQueryException;
    List<Answer> findPossibleAnswersByTaskId(long task_id) throws UnsuccessfulQueryException;
    boolean createAsPossibleAnswer(long taskId, long answerId) throws UnsuccessfulQueryException;
    boolean createAsCorrectAnswer(long taskId, long answerId) throws UnsuccessfulQueryException;
    long createAndGetId(Answer entity) throws UnsuccessfulQueryException;
    boolean update(Answer topic) throws UnsuccessfulQueryException;
}
