package com.javacourse.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionPoolResource {
    private static BasicDataSource ds = new BasicDataSource();
    private static Properties property = new Properties();
    public static final Logger logger;

    static {
        logger = Logger.getLogger(DatabaseConnectionPoolResource.class);
        //DOMConfigurator.configure("E:\\Epam Java Course\\Homework\\JavaExternal\\Homework_24_11_18\\log\\log4j.xml");
    }

    static {
        FileInputStream fis =null;
        try {
            fis = new FileInputStream("C:\\Users\\kois\\Desktop\\Final_Project\\src\\main\\resources\\database.properties");
            property.load(fis);
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl(property.getProperty("db.host"));
            ds.setUsername(property.getProperty("db.user"));
            ds.setPassword(property.getProperty("db.password"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private DatabaseConnectionPoolResource(){}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
