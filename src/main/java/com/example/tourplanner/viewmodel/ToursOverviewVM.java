package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.collections.ObservableList;


public class ToursOverviewVM {

    TourDao tourDao = new TourDao();


    public ToursOverviewVM(){}

    public ObservableList<Tour> getObservableTours() {
       TourListSingleton.getInstance().setTourList(tourDao.getAll()) ;
        return TourListSingleton.getInstance().getTourlist();
    }

    public void deleteTour(){
        tourDao.delete(TourManager.Instance().getSelectedTour());
    }

}
