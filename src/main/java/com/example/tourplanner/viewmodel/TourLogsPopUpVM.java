package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.*;

public class TourLogsPopUpVM {


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

    public void addTourLog() {
        TourLog tourLog = new TourLog(TourManager.SelectTourEventInstance().getSelectedTour(), getDateInput().get(), getTourDurationInput().get(), Integer. parseInt(getDistanceInput().get()) ,getCommentInput().get(), Integer. parseInt(getRatingInput().get()));

        DAL.getInstance().tourLogsDao().create(tourLog);

     //   TourManager.ToursViewManager().fireEvent();

    }


}
