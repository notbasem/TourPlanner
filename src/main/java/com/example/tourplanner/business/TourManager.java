package com.example.tourplanner.business;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    List<EventListener> eventListenerList;
    String selectedTour;
    private static TourManager selectTourEventInstance;
    private static TourManager toursViewManager;


    public void addListener(EventListener listener){
        this.eventListenerList.add(listener);
    }

    public void fireEvent(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onEvent();
        }
    }

    public void selectTour(String name){
        this.selectedTour = name;
        this.fireEvent();
    }

    public String getSelectedTour(){
        return this.selectedTour;
    }

    public static TourManager SelectTourEventInstance() {
        if (selectTourEventInstance == null) {
            selectTourEventInstance = new TourManager();
            selectTourEventInstance.init();
        }
        return selectTourEventInstance;
    }

    public static TourManager ToursViewManager() {
        if (toursViewManager == null) {
            toursViewManager = new TourManager();
            toursViewManager.init();
        }
        return toursViewManager;
    }

    private void init() {
        this.eventListenerList = new ArrayList<>();
    }

}
