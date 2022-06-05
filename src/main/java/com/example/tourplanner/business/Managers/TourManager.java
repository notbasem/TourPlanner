package com.example.tourplanner.business.Managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private static final Logger logger = LogManager.getLogger(TourManager.class.getSimpleName());
    private List<EventListener> eventListenerList;
    private String selectedTour;
    private int selectedTourLog;
    private static TourManager Instance;
    private String searchText;

    public void addListener(EventListener listener) {
        logger.info("Adding listener " + listener.getClass().getSimpleName() + " to eventListenerList");
        this.eventListenerList.add(listener);
    }

    public void fireEvent() {
        for (EventListener eventListener : eventListenerList) {
            eventListener.onEvent();
        }
    }

    public void fireAddedLogEvent() {
        for (EventListener eventListener : eventListenerList) {
            eventListener.onAddedTourLogEvent();
        }
    }

    public void fireAddedTourEvent() {
        for (EventListener eventListener : eventListenerList) {
            eventListener.onAddedTour();
        }
    }

    public void fireUpdateLogEvent() {
        for (EventListener eventListener : eventListenerList) {
            eventListener.updateTourLog();
        }
    }

    public void selectTourLog(int id) {
        logger.info("Selected TourLog with id = " + id);
        this.selectedTourLog = id;
    }

    public void selectTour(String name) {
        logger.info("Selected Tour " + name);
        this.selectedTour = name;
        this.fireEvent();
    }

    public String getSelectedTour() {
        return this.selectedTour;
    }

    public int getSelectedTourLog() {
        return this.selectedTourLog;
    }

    public static TourManager Instance() {
        if (Instance == null) {
            Instance = new TourManager();
            Instance.init();
        }
        return Instance;
    }

    public void onSearch(String searchText) {
        logger.info("Searched for tours containing: " + searchText);
        this.searchText = searchText;
        this.fireOnSearch();
    }

    public String getSearch() {
        return this.searchText;
    }

    public void fireOnSearch() {
        for (EventListener eventListener : eventListenerList) {
            eventListener.onSearch();
        }
    }

    private void init() {
        this.eventListenerList = new ArrayList<>();
    }
}
