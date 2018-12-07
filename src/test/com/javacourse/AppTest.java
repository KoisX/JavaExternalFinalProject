package com.javacourse;

import com.javacourse.utils.DatabaseConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


//TODO get rid of it
public class AppTest {
    private static Logger logger = Logger.getLogger(DatabaseConnectionManager.class);

    //configuring logger
    static {
        new DOMConfigurator().doConfigure(Constants.LOG_CONFIG, LogManager.getLoggerRepository());
    }

    @Test
    public void name() {
        logger.info("test4");
    }
}
