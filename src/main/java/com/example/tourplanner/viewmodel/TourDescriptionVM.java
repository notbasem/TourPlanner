package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class TourDescriptionVM implements EventListener {
    public TourDescriptionVM(){
        TourManager.Instance().addListener(this);
    }

    private final StringProperty tourNameInput = new SimpleStringProperty();


    Optional<Tour> tour;

    TourDao tourDao = new TourDao();


    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = tourDao.get(tourname);
        return tour;
    }

    @Override
    public void onEvent() {
       this.tour= this.tourDao.get(TourManager.Instance().getSelectedTour());
       System.out.println("Event was fired yaaaay");

    }

    public Optional<Tour> getTour() {
        return tour;
    }
}
