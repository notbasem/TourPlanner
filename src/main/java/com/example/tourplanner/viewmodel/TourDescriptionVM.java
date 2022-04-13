package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class TourDescriptionVM {
    TourDao tourDao = new TourDao();

    private final StringProperty name = new SimpleStringProperty();//observable type--> that's why functions like .get can be used in order to return he current value of tourname

    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = tourDao.get(tourname);
        return tour;
    }



}
