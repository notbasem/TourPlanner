package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.*;

import java.io.IOException;
import java.util.Optional;

public class PopUpVM implements EventListener {
    ApiConnection apiConnection = new ApiConnection();

    public  PopUpVM(){
        TourManager.UpdateTourRouteImage().addListener(this);
    }

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

    public void addTour() throws IOException, InterruptedException {
        System.out.println("ADDED TOUR " + tourNameInput.get());

        String link = apiConnection.sendRequest(fromInput.get(),toInput.get()).replaceAll(" ", "%20");

        Tour tour = new Tour(tourNameInput.get(), tourDescriptionInput.get(), fromInput.get(), toInput.get(), transportTypeInput.get(), apiConnection.getDistance(),apiConnection.getTime(), link);
        apiConnection.getTime();
        DAL.getInstance().tourDao().create(tour);

        TourManager.ToursViewManager().fireEvent();

    }

    @Override
    public void onEvent() {
        Optional<Tour> tour=DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour()) ;

        try {
            System.out.println("OLdlink"+tour.get().getRouteInformation());

            String newlink = apiConnection.sendRequest(tour.get().getFrom(),tour.get().getTo()).replaceAll(" ", "%20");

            tour.get().setRouteInformation(newlink);
            System.out.println("Newlink "+tour.get().getRouteInformation());

            DAL.getInstance().tourDao().update(tour.get(),tour.get().getName());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Imagelink updated");
    }

    @Override
    public void onSearch() {

    }
}
