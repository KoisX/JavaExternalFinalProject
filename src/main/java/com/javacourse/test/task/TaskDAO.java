package com.javacourse.test.task;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.AbstractDAO;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface TaskDAO extends AbstractDAO<Integer, Task> {
    List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException;
    int getMaximalScoreByTestId(String test_id) throws UnsuccessfulQueryException;
}
