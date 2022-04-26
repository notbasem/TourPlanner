package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewVM {
    //TourDao tourDao = new TourDao();
    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();

    public ToursOverviewVM(){
        setTours(DAL.getInstance().tourDao().getAll());
    }

    public ObservableList<Tour> getObservableTours() {
        return observableTours;
    }

    public ChangeListener<Tour> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Tour newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<Tour> tours) {
        observableTours.clear();
        observableTours.addAll(tours);
    }

    public void addNewTour(Tour tour) {

        DAL.getInstance().tourDao().create(tour);
        observableTours.add(tour);
    }

}
