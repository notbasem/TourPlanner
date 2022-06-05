package com.example.tourplanner.DAL.dal.config;

import com.example.tourplanner.business.Managers.TourManager;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbConnection implements Closeable {
    private static final Logger logger = LogManager.getLogger(TourManager.class.getSimpleName());
    private static DbConnection instance;
    private Connection connection;
    private Properties properties = new Properties();

    public DbConnection() {
        try {
            this.properties = getProperties("src/main/java/com/example/tourplanner/DAL/dal/config/connection.properties");
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQL JDBC driver not found");
            e.printStackTrace();
        }
    }

    protected Properties getProperties(String path) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
            logger.info("Properties successfully read");
        } catch (IOException e) {
            logger.error("Properties could not be read");
            e.printStackTrace();
            return null;
        }
        return prop;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );

    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DbConnection.getInstance().connect();
                logger.info("Connection to database successful");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            connection = null;
        }
    }

    public static DbConnection getInstance() {
        if (instance == null)
            instance = new DbConnection();
        return instance;
    }
}
