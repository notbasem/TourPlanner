package com.example.tourplanner.business;

import com.example.tourplanner.DAL.model.Tour;

public interface TourListener {
    void listChanged(Tour tour);
}
