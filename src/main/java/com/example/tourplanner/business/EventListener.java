package com.example.tourplanner.business;

public interface EventListener {
    void onEvent();
    void onAddedTourLogEvent();
    void onSearch();
    void updateTourLog();
    void onAddedTour();
}
