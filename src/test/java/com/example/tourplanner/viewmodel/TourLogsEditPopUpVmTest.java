package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.TourManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TourLogsEditPopUpVmTest {


    @Test
    public void updateTourLog(){
        PopUpVM popUpVM = new PopUpVM();

        popUpVM.getTourNameInput().set("tourName1");
        popUpVM.getTourDescriptionInput().set("tourDescription");
        popUpVM.getFromInput().set("Vienna");
        popUpVM.getToInput().set("Graz");
        popUpVM.getTransportTypeInput().set("Train");

        popUpVM.addTour();

        TourManager.Instance().selectTour(popUpVM.getTourNameInput().get());

        TourLogsPopUpVM tourLogsPopUpVM = new TourLogsPopUpVM();

        tourLogsPopUpVM.getTourDurationInput().set("3 hours");
        tourLogsPopUpVM.getDistanceInput().set("35 kilometers");
        tourLogsPopUpVM.getCommentInput().set("interesting");
        tourLogsPopUpVM.getRatingInput().set("3");
        tourLogsPopUpVM.getDifficultyInput().set("5");
        TourLog tourLog = new TourLog(TourManager.Instance().getSelectedTour(), tourLogsPopUpVM.getTourDurationInput().get(), tourLogsPopUpVM.getDistanceInput().get(),tourLogsPopUpVM.getCommentInput().get(), Integer.parseInt(tourLogsPopUpVM.getRatingInput().get()) ,Integer.parseInt( tourLogsPopUpVM.getDifficultyInput().get()));


        tourLogsPopUpVM.addTourLog();

        tourLog.setDate(DAL.getInstance().tourLogsDao.getLogDate(tourLog,TourManager.Instance().getSelectedTour()));

        int logId = DAL.getInstance().tourLogsDao().getLogId(tourLog, TourManager.Instance().getSelectedTour());
        TourManager.Instance().selectTourLog(logId);
        TourLog tourLogold = DAL.getInstance().tourLogsDao.getLogById(logId);

        TourLogsEditPopUpVM tourLogsEditPopUpVM = new TourLogsEditPopUpVM();
        tourLogsEditPopUpVM.getTourDurationInput().set("5 hours");
        tourLogsEditPopUpVM.getDistanceInput().set("35 kilometers");
        tourLogsEditPopUpVM.getCommentInput().set("interesting");
        tourLogsEditPopUpVM.getRatingInput().set("5");
        tourLogsEditPopUpVM.getDifficultyInput().set("8");


        tourLogsEditPopUpVM.updateLog();

        TourLog tourLognew = DAL.getInstance().tourLogsDao.getLogById(logId);

        System.out.println(tourLognew.getRating());
        System.out.println(tourLogold.getRating());

        int rating1 =tourLognew.getRating();
        int rating2 = tourLogold.getRating();
        assertNotEquals(rating2,rating1);
    }
}
