package com.example.tourplanner.business;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    List<EventListener> eventListenerList;
    String selectedTour;
    private static TourManager instance;

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

    public static TourManager Instance() {
        if (instance == null) {
            instance = new TourManager();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.eventListenerList = new ArrayList<>();
    }

}
