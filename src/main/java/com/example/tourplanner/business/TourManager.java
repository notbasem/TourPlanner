package com.example.tourplanner.business;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    List<EventListener> eventListenerList;
    String selectedTour;
    int selectedTourLog;

    private static TourManager selectClickedTourEventInstance;

    public void addListener(EventListener listener){
        this.eventListenerList.add(listener);
    }

    public void fireEvent(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onEvent();
        }
    }


    public void fireAddedLogEvent(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onAddedTourLogEvent();
        }
    }

    public void fireClickedLogEvent(){
        for(EventListener eventListener : eventListenerList){
            eventListener.onclickedTourLog();
        }
    }


    public void selectTourLog(int id){
        this.selectedTourLog = id;
        this.fireAddedLogEvent();
    }



    public void selectTour(String name){
        this.selectedTour = name;
        this.fireEvent();
    }

    public String getSelectedTour(){
        return this.selectedTour;
    }

    public int getSelectedTourLog(){
        return this.selectedTourLog;
    }


    public static TourManager SelectTourEventInstance() {
        if (selectClickedTourEventInstance == null) {
            selectClickedTourEventInstance = new TourManager();
            selectClickedTourEventInstance.init();
        }
        return selectClickedTourEventInstance;
    }

    private void init() {
        this.eventListenerList = new ArrayList<>();
    }

}
