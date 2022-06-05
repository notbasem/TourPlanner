package com.example.tourplanner.viewmodel;
import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.EventListener;
import com.example.tourplanner.business.Managers.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TourLogsVM implements EventListener {
    private ObservableList<TourLog> tourlogs = FXCollections.observableArrayList();
    private final StringProperty searchString = new SimpleStringProperty();

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

    public String getSearchString() {
        return searchString.get();
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    public void searchTourLog() {
        ObservableList<TourLog> tempLogs = FXCollections.observableArrayList();
        tempLogs.addAll(DAL.getInstance().tourLogsDao().getlogs(TourManager.Instance().getSelectedTour()));

        if (searchString.get() == null) {
            tourlogs.setAll((TourLog) this.getObservableTours());
        } else {
            tourlogs.setAll(tempLogs.stream().filter(log -> log.toSearchString().contains(searchString.get())).toList());
        }
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
