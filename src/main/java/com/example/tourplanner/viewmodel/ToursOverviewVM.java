package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
//import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;


public class ToursOverviewVM  {

    public ToursOverviewVM(){}

    public ObservableList<Tour> getObservableTours() {
        //TourListSingleton.getInstance().setTourList((ObservableList<Tour>) DAL.getInstance().tourDao().getAll()) ;
       // return TourListSingleton.getInstance().getTourlist();
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public void deleteTour(){
        DAL.getInstance().tourDao().delete(TourManager.SelectTourEventInstance().getSelectedTour());
    }
}
