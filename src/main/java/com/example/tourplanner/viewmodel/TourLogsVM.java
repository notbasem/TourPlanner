package com.example.tourplanner.viewmodel;
import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.EventListener;
import com.example.tourplanner.business.Managers.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogsVM implements EventListener {
    private ObservableList<TourLog> tourlogs = FXCollections.observableArrayList();

    public TourLogsVM() {
        TourManager.Instance().addListener(this);
    }

    public Property<ObservableList<TourLog>> getObservableTours() {
        setTourlogs(TourManager.Instance().getSelectedTour());
        Property<ObservableList<TourLog>> tourListProperty = new SimpleObjectProperty<>(tourlogs);

        return tourListProperty;
    }

    public void setTourlogs(String name) {
        this.tourlogs = DAL.getInstance().tourLogsDao().getlogs(name);
    }

    public int getIdOfTour(TourLog tourLog, String tourName) {
        return DAL.getInstance().tourLogsDao.getLogId(tourLog,tourName);
    }

    @Override
    public void onEvent() {
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.Instance().getSelectedTour()));
    }

    @Override
    public void onAddedTourLogEvent() {
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.Instance().getSelectedTour()));
    }

    @Override
    public void updateTourLog() {
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.Instance().getSelectedTour()));

    }

    public void deleteTourLog() {
        DAL.getInstance().tourLogsDao.deletebyid(TourManager.Instance().getSelectedTourLog());
        tourlogs.setAll(DAL.getInstance().tourLogsDao.getlogs(TourManager.Instance().getSelectedTour()));
    }

    @Override
    public void onAddedTour() {}

    @Override
    public void onSearch() {}
}
