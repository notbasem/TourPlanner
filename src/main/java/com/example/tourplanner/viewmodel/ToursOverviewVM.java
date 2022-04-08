package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.model.Tour;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewVM {
    TourDescriptionVM tourDescriptionVM;

    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableTours= FXCollections.observableArrayList();

    public ToursOverviewVM(){
        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour("Tour1"));
        observableTours.clear();
        observableTours.addAll(tours);
    }

    public ObservableList<Tour> getObservableTours() {
        return observableTours;
    }

    public ObservableList<Tour> addTour(String tourName) {
        System.out.println("ADDED TOUR " + tourName);
        observableTours.add(new Tour(tourName));
        return observableTours;
    }
}
