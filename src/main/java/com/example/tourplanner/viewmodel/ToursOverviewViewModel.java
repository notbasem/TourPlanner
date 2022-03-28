package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewViewModel {
    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableTours= FXCollections.observableArrayList();
    public ToursOverviewViewModel(){}

    public ObservableList<Tour> getObservableTours() {
        return observableTours;
    }










}
