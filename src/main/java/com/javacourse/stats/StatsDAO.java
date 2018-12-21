package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.dataAccess.AbstractDAO;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface StatsDAO extends AbstractDAO<Integer, Stats> {
    List<Stats> findAllWithPagination(int offset, int recordsPerPage) throws UnsuccessfulQueryException;
    int getNumberOfPages(int recordsPerPage) throws UnsuccessfulQueryException;
}
