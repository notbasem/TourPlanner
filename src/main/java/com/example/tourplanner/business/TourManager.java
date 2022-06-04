package com.example.tourplanner.business;


import java.util.ArrayList;
import java.util.List;

public class TourManager {
    List<EventListener> eventListenerList;
    String selectedTour;
    int selectedTourLog;

    private static TourManager Instance;
    private String searchText;

    public void addListener(EventListener listener) {
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
        this.selectedTourLog = id;
    }

    public void selectTour(String name) {
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
