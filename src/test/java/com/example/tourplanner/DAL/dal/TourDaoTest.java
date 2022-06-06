package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.model.Tour;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TourDaoTest {
    private Tour tour;

    @BeforeAll
    public void setup() {
        String name = RandomStringUtils.random(8, true, false);
        this.tour = new Tour(name, "Beautiful Tour", "Vienna", "Paris", "Car", 100.0F, "12");
        DAL.getInstance().tourDao().create(tour);
    }

    @Test
    @Order(1)
    void getTrue() {
        assertEquals(tour.tourToString(), DAL.getInstance().tourDao().get(tour.getName()).get().tourToString());
    }

    @Test
    @Order(2)
    void update() {
        Tour newTour = new Tour("NEW TOUR", "Beautiful Tour", "Vienna", "Paris", "Car", 100.0F, "12");
        DAL.getInstance().tourDao().update(tour, newTour);
        DAL.getInstance().tourDao().get(newTour.getName());
    }

    @Test
    @Order(3)
    void delete() {
        DAL.getInstance().tourDao().delete("NEW TOUR");
    }
}