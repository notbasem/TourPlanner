package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.*;


public class PopUpVM  {

    public  PopUpVM(){
    }

    private final StringProperty tourNameInput = new SimpleStringProperty();
    private final StringProperty tourDescriptionInput = new SimpleStringProperty();
    private final StringProperty fromInput = new SimpleStringProperty();
    private final StringProperty toInput = new SimpleStringProperty();
    private final StringProperty transportTypeInput = new SimpleStringProperty();

    public StringProperty gettourNameInput() {
        return tourNameInput;
    }

    public StringProperty getTourDescriptionInput() {
        return tourDescriptionInput;
    }

    public StringProperty getfromInput() {
        return fromInput;
    }

    public StringProperty gettoInput() {
        return toInput;
    }

    public StringProperty gettransportTypeInput() {
        return transportTypeInput;
    }

    public void addTour() {
        ApiConnection apiConnection = new ApiConnection(fromInput.get(), toInput.get());
        System.out.println("ADDED TOUR " + tourNameInput.get());

        Tour tour = new Tour(tourNameInput.get(), tourDescriptionInput.get(), fromInput.get(), toInput.get(), transportTypeInput.get(),apiConnection.getDistance(), apiConnection.getTime());
        DAL.getInstance().tourDao().create(tour);

        TourManager.SelectTourEventInstance().fireAddedTourEvent();

    }

}
