package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewVM {
    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableTours= FXCollections.observableArrayList();
    public ToursOverviewVM(){}

    public ObservableList<Tour> getObservableTours() {
        return observableTours;
    }










}
