package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.PdfManager;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class MenuVM implements EventListener {
    private final StringProperty searchString = new SimpleStringProperty();

    public MenuVM() {}

    public ObservableList<Tour> exportallTours() {
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public void exportAllToursAndLogs() {

        ObservableList<Tour> tourList = (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
        ObservableList <TourLog> tourLogsList = (ObservableList<TourLog>) DAL.getInstance().tourLogsDao.getAll();

        PdfManager.getInstance().exportAlltours(tourList,tourLogsList);
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    @Override
    public void onSearch() {
        TourManager.SelectTourEventInstance().onSearch(searchString.get());
    }

    @Override
    public void updateTourLog() {}

    @Override
    public void onAddedTour() {}

    @Override
    public void onEvent() {}

    @Override
    public void onAddedTourLogEvent() {}
}
