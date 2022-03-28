package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourDescriptionViewModel {
    private Tour tour;
    private volatile boolean isInitValue = false;//volatile ?????????????????????????????????????

    private final StringProperty name = new SimpleStringProperty();//observable type--> that's why functions like .get can be used in order to return he current value of tourname

    public TourDescriptionViewModel(){name.addListener((arg, oldVal, newVal)->updateTourModel());
    }

    public void setTourModel( Tour tour) {
        isInitValue = true;
        if (tour == null) {
            // select the first in the list
            name.set("");
            return;
        }
        System.out.println("setTourModel name=" + tour.getName() );
        this.tour = tour;
        name.setValue( tour.getName() );
        isInitValue = false;
    }
    public StringProperty nameProperty() {
        return name;
    }

    public void updateTourModel(){
        if( !isInitValue ){
            DAL.getInstance().tourDao().update(tour,name.get());
        } else{

            setTourModel(tour);
            DAL.getInstance().tourDao().create(tour);
        }
        }


}
