package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.*;
import lombok.Getter;
@Getter


public class PopUpVM {

    public PopUpVM() {
    }

    private final StringProperty tourNameInput = new SimpleStringProperty();
    private final StringProperty tourDescriptionInput = new SimpleStringProperty();
    private final StringProperty fromInput = new SimpleStringProperty();
    private final StringProperty toInput = new SimpleStringProperty();
    private final StringProperty transportTypeInput = new SimpleStringProperty();

    public StringProperty getTourNameInput() {
        return tourNameInput;
    }

    public StringProperty getTourDescriptionInput() {
        return tourDescriptionInput;
    }

    public StringProperty getFromInput() {
        return fromInput;
    }

    public StringProperty getToInput() {
        return toInput;
    }

    public StringProperty getTransportTypeInput() {
        return transportTypeInput;
    }

    public void addTour() {
        System.out.println("VALID INPUT");
        ApiConnection apiConnection = new ApiConnection(fromInput.get(), toInput.get());
        System.out.println("ADDED TOUR " + tourNameInput.get());

        Tour tour = new Tour(tourNameInput.get(), tourDescriptionInput.get(), fromInput.get(), toInput.get(), transportTypeInput.get(), apiConnection.getDistance(), apiConnection.getTime());
        DAL.getInstance().tourDao().create(tour);

        TourManager.Instance().fireAddedTourEvent();
    }
}
