package com.example.tourplanner.business.API;

import com.example.tourplanner.business.EventListener;

import java.util.ArrayList;
import java.util.List;

public class AddedTourManager {

    List< AddedTourEventListener> eventListenerList;
    private static AddedTourManager addedTourManager;
    String searchText;

    public void addListener( AddedTourEventListener listener) {
        this.eventListenerList.add(listener);
    }

    public void fireEvent() {
        for ( AddedTourEventListener eventListener : eventListenerList) {
            eventListener.onEvent();
        }
    }


    public static AddedTourManager getAddedTourManager() {
        if (addedTourManager == null) {
            addedTourManager = new AddedTourManager();
            addedTourManager.init();
        }
        return addedTourManager;
    }


    private void init() {
        this.eventListenerList = new ArrayList<>();
    }


    public void onSearch(String searchText){
        this.searchText = searchText;
        this.fireOnSearch();
    }
    public String getSearch(){
        return this.searchText;
    }

    public void fireOnSearch(){
        for(AddedTourEventListener eventListener : eventListenerList){
            eventListener.onSearch();
        }
    }
}
