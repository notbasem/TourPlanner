package com.example.tourplanner.business;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    List<EventListener> eventListenerList;
    String selectedTour;
    String searchText;

    private static TourManager selectClickedTourEventInstance;
    private static TourManager toursViewManager;

    public void addListener(EventListener listener){
        this.eventListenerList.add(listener);
    }

    public void fireEvent(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onEvent();
        }
    }

    public void fireOnSearch(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onSearch();
        }
    }

    public void selectTour(String name){
        this.selectedTour = name;
        this.fireEvent();
    }

    public void onSearch(String searchText){
        this.searchText = searchText;
        this.fireOnSearch();
    }

    public String getSelectedTour(){
        return this.selectedTour;
    }

    public String getSearch(){
        return this.searchText;
    }

    public static TourManager SelectTourEventInstance() {
        if (selectClickedTourEventInstance == null) {
            selectClickedTourEventInstance = new TourManager();
            selectClickedTourEventInstance.init();
        }
        return selectClickedTourEventInstance;
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
