package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.ObservableList;

public class MenuVM {
    TourDao tourDao = new TourDao();

    public MenuVM(){}

    public ObservableList<Tour> exportallTours(){
      return  tourDao.getAll();
    }

}
