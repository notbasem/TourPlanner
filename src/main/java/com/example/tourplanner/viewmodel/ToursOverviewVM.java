package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewVM {

    TourDao tourDao = new TourDao();


    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();


    public ToursOverviewVM(){}

    public ObservableList<Tour> getObservableTours() {
       TourListSingleton.getInstance().setTourList(tourDao.getAll()) ;
        return TourListSingleton.getInstance().getTourlist();
    }

}
