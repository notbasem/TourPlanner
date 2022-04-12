package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class PopUpVM {
    private final StringProperty tourNameInput = new SimpleStringProperty();
    TourDao tourDao = new TourDao();


    public StringProperty tourNameInput(){
        return tourNameInput;
    }


    public  ObservableList<Tour> addTour() {
        System.out.println("ADDED TOUR " + tourNameInput.get());
        String enteredTourName = tourNameInput.get();
        Tour tour = new Tour(enteredTourName);
        tour.setDefaultValues(tour);
        tourDao.create(tour);
        TourListSingleton.getInstance().getTourlist().add(tour);
       // observableTours.add(tour);
       // return observableTours;
        return TourListSingleton.getInstance().getTourlist();
    }













}
