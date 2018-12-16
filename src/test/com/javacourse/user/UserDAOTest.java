package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.TestDAO;
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

    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAO(roleFactory);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findAll_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAO.findAll();
    }

    @Test
    public void parseToEntityList_getsEntity_returnsCorrectEntity() throws SQLException {
        User user = new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Role.USER, "123");
        Role role = Role.USER;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong(1)).thenReturn(user.getId());
        when(rs.getString(2)).thenReturn(user.getName());
        when(rs.getString(3)).thenReturn(user.getSurname());
        when(rs.getString(4)).thenReturn(user.getEmail());
        when(rs.getString(5)).thenReturn(role.getName());
        when(rs.getLong(6)).thenReturn(role.getId());
        when(rs.getString(7)).thenReturn(user.getPassword());
        when(roleFactory.createRole(user.getRole().getName(), user.getRole().getId())).thenReturn(Role.USER);

        List<User> expected = new LinkedList<>();
        expected.add(user);
        List<User> actual = userDAO.parseToEntityList(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void findById_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAO.findById(anyInt());
    }

    @Test
    public void parseSingleEntity_getsEntity_returnsCorrectEntity() throws SQLException {
        User expected = new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Role.USER,"123");
        Role role = Role.USER;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong(1)).thenReturn(expected.getId());
        when(rs.getString(2)).thenReturn(expected.getName());
        when(rs.getString(3)).thenReturn(expected.getSurname());
        when(rs.getString(4)).thenReturn(expected.getEmail());
        when(rs.getString(5)).thenReturn(role.getName());
        when(rs.getLong(6)).thenReturn(role.getId());
        when(rs.getString(7)).thenReturn(expected.getPassword());
        when(roleFactory.createRole(expected.getRole().getName(), expected.getRole().getId())).thenReturn(Role.USER);

        User actual = userDAO.parseSingleEntity(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void delete_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAO.delete(anyInt());
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void create_unableToPrepareConnectionStatement_throwCustomException() throws SQLException, UnsuccessfulQueryException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
        userDAO.create(anyObject());
    }

    @Test
    @Ignore
    public void update() {
    }
}