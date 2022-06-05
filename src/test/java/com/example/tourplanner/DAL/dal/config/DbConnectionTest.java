package com.example.tourplanner.DAL.dal.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DbConnectionTest {
    private DbConnection dbConnection;
    private String path;

    @BeforeAll
    public void setup() {
        this.dbConnection = DbConnection.getInstance();
        this.path = "src/test/java/com/example/tourplanner/DAL/dal/config/connection.properties";
    }

    @Test
    void getInstance() {
        assertEquals(dbConnection, DbConnection.getInstance());
    }

    @Test
    void getPropertiesTrue() {
        Properties properties = new Properties();
        properties.put("driver", "org.postgresql.Driver");
        properties.put("url", "jdbc:postgresql://localhost:5432/toursdb");
        properties.put("user", "root");
        properties.put("password", "root123");
        assertEquals(properties, dbConnection.getProperties(path));
    }

    @Test
    void getPropertiesFalse() {
        Properties properties = new Properties();
        properties.put("driver", "false");
        properties.put("url", "false");
        properties.put("user", "false");
        properties.put("password", "false");
        assertNotEquals(properties, dbConnection.getProperties(path));
    }

    @Test
    void getPropertiesWrongPathFalse() {
        assertNull(dbConnection.getProperties("/wrong/path"));
    }


}