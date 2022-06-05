package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.TourManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TourLogsEditPopUpVM {
    private static final Logger logger = LogManager.getLogger(TourDescriptionVM.class.getSimpleName());
    private final StringProperty dateInput = new SimpleStringProperty();
    private final StringProperty durationInput = new SimpleStringProperty();
    private final StringProperty distanceInput = new SimpleStringProperty();
    private final StringProperty commentInput = new SimpleStringProperty();
    private final StringProperty ratingInput = new SimpleStringProperty();
    private final StringProperty difficultyInput = new SimpleStringProperty();


   public StringProperty getDateInput() {
     return dateInput;
    }

   /* public String getDateInput() {
        int tourLogId = TourManager.Instance().getSelectedTourLog();
        TourLog tourLog = DAL.getInstance().tourLogsDao.getLogById(tourLogId);
        dateInput.setValue(tourLog.getDate());
        return dateInput.toString();
    }*/
    public StringProperty getTourDurationInput() {
        return durationInput;
    }

    public StringProperty getDistanceInput() {
        return distanceInput;
    }

    public StringProperty getCommentInput() {
        return commentInput;
    }

    public StringProperty getRatingInput() {
        return ratingInput;
    }

    public StringProperty getDifficultyInput() {
        return difficultyInput;
    }


    public void setData() {
        int tourLogId = TourManager.Instance().getSelectedTourLog();
        TourLog tourLog = DAL.getInstance().tourLogsDao.getLogById(tourLogId);
        dateInput.setValue(tourLog.getDate());
        durationInput.setValue(tourLog.getDuration());
        distanceInput.setValue(String.valueOf(tourLog.getDistance()));
        commentInput.setValue(tourLog.getComment());
        ratingInput.setValue(String.valueOf(tourLog.getRating()));
        difficultyInput.setValue(String.valueOf(tourLog.getDifficulty()));


        System.out.println("dateInput: "+dateInput+"durationinput:   "+durationInput+""+distanceInput+" "+commentInput+" "+ratingInput+" "+difficultyInput);

    }

    public void updateLog() {
        TourLog oldTourLog = DAL.getInstance().tourLogsDao.getLogById(TourManager.Instance().getSelectedTourLog());

        TourLog newTourLog = new TourLog(oldTourLog.getTourname(), getTourDurationInput().get(), getDistanceInput().get(), getCommentInput().get(), Integer.parseInt(getRatingInput().get()), Integer.parseInt(getDifficultyInput().get()));

        DAL.getInstance().tourLogsDao.update(oldTourLog, newTourLog);
        TourManager.Instance().fireUpdateLogEvent();
    }
}
