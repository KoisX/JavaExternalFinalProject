package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.AbstractDAO;

import java.util.List;

public interface StatsDAO extends AbstractDAO<Integer, Stats> {
    List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException;
    int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException;
}
