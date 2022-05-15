package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;

import java.io.IOException;
import java.util.Optional;

public class TourDescriptionVM implements EventListener {
    public TourDescriptionVM(){
        TourManager.SelectTourEventInstance().addListener(this);
    }


    Optional<Tour> tour;


    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = DAL.getInstance().tourDao().get(tourname);

        return tour;
    }

    @Override
    public void onEvent() {
       this.tour= DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour());
       System.out.println("Event was fired yaaaay");
    }

    public String apiThingy(){
        ApiConnection apiConnection = new ApiConnection();
        try {
          return  apiConnection.sendRequest(tour.get().getFrom(),tour.get().getTo());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }
    public Optional<Tour> getTour() {
        return tour;
    }
}
