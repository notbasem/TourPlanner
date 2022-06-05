package com.example.tourplanner.business.API;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiConnectionTest {
    ApiConnection apiConnection;

    @BeforeAll
    public void setup() {
        this.apiConnection = new ApiConnection("Vienna", "Paris");
    }

    @Test
    void getKeyTrue() {
        assertEquals("MDApaG1PWkkep6VbZXPXdc8SWUYy1FFf", apiConnection.getKey());
    }

    @Test
    void getKeyFalse() {
        assertNotEquals("abc False Key", apiConnection.getKey());
    }

    @Test
    void getTimeTrue() {
        assertEquals("11:15:53", apiConnection.getTime());
    }

    @Test
    void getTimeFalse() {
        assertNotEquals("01:01:01", apiConnection.getTime());
    }

    @Test
    void getDistanceTrue() {
        assertEquals(768.885F, apiConnection.getDistance());
    }

    @Test
    void getDistanceFalse() {
        assertNotEquals(12.12F, apiConnection.getDistance());
    }
}