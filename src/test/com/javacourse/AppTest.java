package com.javacourse;

import com.javacourse.utils.DBCPTomcat;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


//TODO get rid of it
public class AppTest {

    @Test
    public void name() throws SQLException {
        Connection connection = DBCPTomcat.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
        connection = DBCPTomcat.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}
