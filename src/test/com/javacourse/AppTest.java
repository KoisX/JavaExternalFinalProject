package com.javacourse;

import com.javacourse.utils.DatabaseConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


//TODO get rid of it
public class AppTest {

    @Test
    public void name() throws SQLException {
        Connection connection = DatabaseConnectionManager.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
        connection = DatabaseConnectionManager.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}
