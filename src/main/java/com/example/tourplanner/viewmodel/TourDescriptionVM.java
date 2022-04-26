package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class TourDescriptionVM {
    private Tour tour;
    TourDao tourDao = new TourDao();
    private volatile boolean isInitValue = false;

    private final StringProperty title = new SimpleStringProperty();//observable type--> that's why functions like .get can be used in order to return he current value of tourname

    /*
    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = tourDao.get(tourname);
        return tour;
    }
    */

    public StringProperty titleProperty() { return title; }

    public void setTour(Tour tour) {
        isInitValue = true;
        if(tour == null) {
            System.out.println("TOUR == NULL");
            // select the first in the list
            title.set("");
            return;
        }
        System.out.println("setTour: name=" + tour.getName());
        this.tour = tour;
        title.setValue(tour.getName());
        isInitValue = false;
    }

}
