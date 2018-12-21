package com.javacourse.test.task;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

import java.util.List;

public interface TaskDAO extends AbstractDAO<Integer, Task> {
    List<Task> findTasksByTestId(String test_id) throws UnsuccessfulQueryException;
}
