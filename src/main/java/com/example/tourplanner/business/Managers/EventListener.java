package com.example.tourplanner.business.Managers;

public interface EventListener {
    void onEvent();
    void onAddedTourLogEvent();
    void onSearch();
    void updateTourLog();
    void onAddedTour();
}
