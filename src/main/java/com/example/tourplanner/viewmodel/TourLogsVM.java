package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import javafx.collections.ObservableList;

public class TourLogsVM {

    public TourLogsVM(){}

    public ObservableList<TourLog> getObservableTours() {
     System.out.println( DAL.getInstance().tourLogsDao().getAll().get(1).getTourname());
        return (ObservableList<TourLog>) DAL.getInstance().tourLogsDao().getAll();
    }
}
