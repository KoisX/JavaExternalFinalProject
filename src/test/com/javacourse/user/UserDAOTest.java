package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private RoleFactory roleFactory;

    private UserDAOMySql userDAOMySql;

    @Before
    public void setUp() throws Exception {
        userDAOMySql = new UserDAOMySql(connection);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAOMySql.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        User user = new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Role.USER, "123");
        Role role = Role.USER;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(user.getId());
        when(rs.getString("name")).thenReturn(user.getName());
        when(rs.getString("surname")).thenReturn(user.getSurname());
        when(rs.getString("email")).thenReturn(user.getEmail());
        when(rs.getString("roleName")).thenReturn(role.getName());
        when(rs.getLong("roleId")).thenReturn(role.getId());
        when(rs.getString("password")).thenReturn(user.getPassword());
        when(roleFactory.createRole(user.getRole().getName(), user.getRole().getId())).thenReturn(Role.USER);

        List<User> expected = new LinkedList<>();
        expected.add(user);
        List<User> actual = userDAOMySql.parseToEntityList(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findById_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAOMySql.findById(anyInt());
    }

    @Test
    public void parseSingleEntity_getsEntity_returnsCorrectEntity() throws SQLException {
        User expected = new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Role.USER,"123");
        Role role = Role.USER;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(expected.getId());
        when(rs.getString("name")).thenReturn(expected.getName());
        when(rs.getString("surname")).thenReturn(expected.getSurname());
        when(rs.getString("email")).thenReturn(expected.getEmail());
        when(rs.getString("roleName")).thenReturn(role.getName());
        when(rs.getLong("roleId")).thenReturn(role.getId());
        when(rs.getString("password")).thenReturn(expected.getPassword());
        when(roleFactory.createRole(expected.getRole().getName(), expected.getRole().getId())).thenReturn(Role.USER);

        User actual = userDAOMySql.parseSingleEntity(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void delete_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAOMySql.delete(anyInt());
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void create_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAOMySql.create(anyObject());
    }
}