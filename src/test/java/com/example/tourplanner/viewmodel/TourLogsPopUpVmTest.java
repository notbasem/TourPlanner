package com.example.tourplanner.viewmodel;

import com.example.tourplanner.business.Managers.TourManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourLogsPopUpVmTest {

    @Test
    public void addTourLog(){
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
        tourLogsPopUpVM.getRatingInput().set("5");
        tourLogsPopUpVM.getDifficultyInput().set("5");

        TourLogsVM tourLogsVM = new TourLogsVM();
        int tourLogsListsize= tourLogsVM.getObservableTourLogs().getValue().size();

        tourLogsPopUpVM.addTourLog();

        int newtourLogsListsize= tourLogsVM.getObservableTourLogs().getValue().size();

        assertEquals(tourLogsListsize+1, newtourLogsListsize);


    }



}
