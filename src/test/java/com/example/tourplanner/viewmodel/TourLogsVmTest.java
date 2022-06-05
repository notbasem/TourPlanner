package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.TourManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourLogsVmTest {

    @BeforeAll
    static void addTour(){
        PopUpVM popUpVM = new PopUpVM();

        popUpVM.getTourNameInput().set("tourName");
        popUpVM.getTourDescriptionInput().set("tourDescription");
        popUpVM.getFromInput().set("Vienna");
        popUpVM.getToInput().set("Graz");
        popUpVM.getTransportTypeInput().set("Train");

        popUpVM.addTour();

        TourManager.Instance().selectTour("tourName");

    }

    @Test
    public void testDeleteTourLog(){

        TourLogsPopUpVM tourLogsPopUpVM = new TourLogsPopUpVM();

        tourLogsPopUpVM.getTourDurationInput().set("3 hours");
        tourLogsPopUpVM.getDistanceInput().set("35 kilometers");
        tourLogsPopUpVM.getCommentInput().set("interesting");
        tourLogsPopUpVM.getRatingInput().set("5");
        tourLogsPopUpVM.getDifficultyInput().set("5");

        TourLog tourLog = new TourLog(TourManager.Instance().getSelectedTour(), tourLogsPopUpVM.getTourDurationInput().get(), tourLogsPopUpVM.getDistanceInput().get(),tourLogsPopUpVM.getCommentInput().get(), Integer.parseInt(tourLogsPopUpVM.getRatingInput().get()) ,Integer.parseInt( tourLogsPopUpVM.getDifficultyInput().get()));

        System.out.println(tourLog.getDate());

        tourLogsPopUpVM.getDateInput().set(tourLog.getDate());
        TourLogsVM tourLogsVM = new TourLogsVM();

        tourLogsPopUpVM.addTourLog();

        int tourLogsListSize = tourLogsVM.getObservableTourLogs().getValue().size();

        tourLog.setDate(DAL.getInstance().tourLogsDao.getLogDate(tourLog,TourManager.Instance().getSelectedTour()));

        int logId = DAL.getInstance().tourLogsDao().getLogId(tourLog, TourManager.Instance().getSelectedTour());
        TourManager.Instance().selectTourLog(logId);
        System.out.println(TourManager.Instance().getSelectedTourLog());

        tourLogsVM.deleteTourLog();

        int newTourLogsListSize = tourLogsVM.getObservableTourLogs().getValue().size();

        System.out.println(newTourLogsListSize);
        assertEquals(tourLogsListSize-1,newTourLogsListSize);

    }



}
