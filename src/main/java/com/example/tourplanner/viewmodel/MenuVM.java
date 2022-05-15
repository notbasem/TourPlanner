package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class MenuVM implements EventListener {
    private final StringProperty searchString = new SimpleStringProperty("Tour");

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
    public void onSearch() {
        TourManager.ToursViewManager().onSearch(searchString.get());
    }
}
