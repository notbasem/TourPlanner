package com.example.tourplanner.viewmodel;

import com.example.tourplanner.business.Managers.TourManager;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToursOverViewVmTest {

    @Test
    public void deleteTourTest() {
        ToursOverviewVM toursOverviewVM = new ToursOverviewVM();

        PopUpVM popUpVM = new PopUpVM();

        popUpVM.getTourNameInput().set("tour");
        popUpVM.getTourDescriptionInput().set("tourDescription");
        popUpVM.getFromInput().set("Vienna");
        popUpVM.getToInput().set("Graz");
        popUpVM.getTransportTypeInput().set("Train");
        popUpVM.addTour();
        int tourListSize = toursOverviewVM.getObservableTours().size();
        TourManager.Instance().selectTour(popUpVM.getTourNameInput().get());

        toursOverviewVM.deleteTour();

        int newtourListSize = toursOverviewVM.getObservableTours().size();

        assertEquals(tourListSize-1, newtourListSize);
    }




}
