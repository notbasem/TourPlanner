package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.AddedTourManager;
import com.example.tourplanner.business.EventListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MenuVM implements EventListener {
    private final StringProperty searchString = new SimpleStringProperty();

    public MenuVM(){
    }

    public ObservableList<Tour> exportallTours(){
      return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onAddedTourLogEvent() {

    }

    @Override
    public void onclickedTourLog() {

    }

    @Override
    public void onSearch() {
        AddedTourManager.getAddedTourManager().onSearch(searchString.get());
    }
}
