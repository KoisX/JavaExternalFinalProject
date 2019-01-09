package com.javacourse.stats;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.user.User;
import com.javacourse.user.role.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsDAOMySqlTest {

    @Mock
    private Connection connection;

    private StatsDAOMySql statsDAOMySql;

    @Before
    public void setUp() throws Exception {
        statsDAOMySql = new StatsDAOMySql(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        statsDAOMySql.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        User user = new User(1, "abc", "abc", "abc", Role.ADMIN, "123");
        //com.javacourse.test.Test test = new com.javacourse.test.Test(1, );
        Stats stats = new Stats(1, new User(), new com.javacourse.test.Test(), 100, new Timestamp(1000));


        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(stats.getId());
        /*when(rs.get)*///TODO ...
    }
}