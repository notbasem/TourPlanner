package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourLogsEditPopUpVM {

    private final StringProperty dateInput = new SimpleStringProperty();
    private final StringProperty durationInput = new SimpleStringProperty();
    private final StringProperty  distanceInput = new SimpleStringProperty();
    private final StringProperty commentInput = new SimpleStringProperty();
    private final StringProperty ratingInput = new SimpleStringProperty();


    public StringProperty getDateInput() {
        return dateInput;
    }

    public StringProperty getTourDurationInput() {
        return durationInput;
    }

    public StringProperty getDistanceInput() {return distanceInput;}

    public StringProperty getCommentInput() { return commentInput;}

    public StringProperty getRatingInput() {
        return ratingInput;
    }


    public void setData(){
        int tourLogId = TourManager.SelectTourEventInstance().getSelectedTourLog();
        TourLog tourLog = DAL.getInstance().tourLogsDao.getLogById(tourLogId);
        dateInput.setValue(tourLog.getDate());
        durationInput.setValue(tourLog.getDuration());
        distanceInput.setValue(String.valueOf(tourLog.getDistance()));
        commentInput.setValue(tourLog.getComment());
        ratingInput.setValue(String.valueOf(tourLog.getRating()));
    }
}
