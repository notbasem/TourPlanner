package com.example.tourplanner.DAL.dal.config;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    @Test
    void connect() {
    }

    @Test
    void getConnection() {
    }

    @Test
    void prepareStatement() {
    }

    @Test
    void close() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void getProperties() {
        DbConnection dbConnection = new DbConnection();
        String path = "src/test/java/com/example/tourplanner/DAL/dal/config/connection.properties";
        Properties properties = new Properties();
        properties.put("driver", "org.postgresql.Driver");
        properties.put("url", "jdbc:postgresql://localhost:5432/toursdb");
        properties.put("user", "root");
        properties.put("password", "root123");
        assertEquals(properties, dbConnection.getProperties(path));
    }
}