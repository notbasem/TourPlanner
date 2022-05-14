package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.ObservableList;

public class MenuVM {

    public MenuVM(){}

    public ObservableList<Tour> exportallTours(){
      return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

}
