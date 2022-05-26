package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.TourManager;
import javafx.collections.ObservableList;


public class ToursOverviewVM  {

    public ToursOverviewVM(){}

    public ObservableList<Tour> getObservableTours() {
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public void deleteTour(){
        DAL.getInstance().tourDao().delete(TourManager.SelectTourEventInstance().getSelectedTour());
    }
}
