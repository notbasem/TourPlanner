package com.example.tourplanner.viewmodel;
import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogsVM implements EventListener {
    private ObservableList<TourLog> tourlogs = FXCollections.observableArrayList();

    public TourLogsVM() {
        TourManager.SelectTourEventInstance().addListener(this);
    }

    public Property<ObservableList<TourLog>> getObservableTours() {
        setTourlogs(TourManager.SelectTourEventInstance().getSelectedTour());
        Property<ObservableList<TourLog>> tourListProperty = new SimpleObjectProperty<>(tourlogs);

        return tourListProperty;
    }

    public void setTourlogs(String name) {
        this.tourlogs = DAL.getInstance().tourLogsDao().getlogs(name);
    }

    public int getIdOfTour(TourLog tourLog, String tourName) {
        return DAL.getInstance().tourLogsDao.getid(tourLog,tourName);
    }

    @Override
    public void onEvent() {
        System.out.println("SELECTED TOURRRRRRRRRRRRRR :" + TourManager.SelectTourEventInstance().getSelectedTour());
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.SelectTourEventInstance().getSelectedTour()));
    }

    @Override
    public void onAddedTourLogEvent() {
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.SelectTourEventInstance().getSelectedTour()));
    }

    @Override
    public void updateTourLog() {
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.SelectTourEventInstance().getSelectedTour()));

    }

    public void deleteTourLog() {
        DAL.getInstance().tourLogsDao.deletebyid(TourManager.SelectTourEventInstance().getSelectedTourLog());
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.SelectTourEventInstance().getSelectedTour()));
    }

    @Override
    public void onAddedTour() {}

    @Override
    public void onSearch() {}
}
