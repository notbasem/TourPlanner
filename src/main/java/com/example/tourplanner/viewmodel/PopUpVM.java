package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class PopUpVM {
    private final StringProperty tourNameInput = new SimpleStringProperty();
    private final StringProperty tourDescriptionInput = new SimpleStringProperty();
    private final StringProperty fromInput = new SimpleStringProperty();
    private final StringProperty toInput = new SimpleStringProperty();
    private final StringProperty transportTypeInput = new SimpleStringProperty();
    private final StringProperty distanceInput = new SimpleStringProperty();
    private final StringProperty estimatedTimeInput = new SimpleStringProperty();
    private final StringProperty routeInformation = new SimpleStringProperty();



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

    public Property<String> getdistanceInput() {
        return distanceInput;
    }

    public Property<String> getestimatedTimeInput() {
        return estimatedTimeInput;
    }

    public StringProperty getrouteInformation() {
        return routeInformation;
    }

    public void addTour() {
        System.out.println("ADDED TOUR " + tourNameInput.get());

        float distance = Float.parseFloat(distanceInput.get());
        int estimatedTime = Integer.parseInt(estimatedTimeInput.get());

       /* if(estimatedTime%1 == 0){
            System.out.println("It is an integer");
        }else{
            System.out.println("not an integer");
        }*/

        Tour tour = new Tour(tourNameInput.get(), tourDescriptionInput.get(), fromInput.get(), toInput.get(), transportTypeInput.get(), distance, estimatedTime, routeInformation.get());
        DAL.getInstance().tourDao().create(tour);

        TourManager.ToursViewManager().fireEvent();
       // TourListSingleton.getInstance().getTourlist().add(tour);

     //   return TourListSingleton.getInstance().getTourlist();
    }

}
