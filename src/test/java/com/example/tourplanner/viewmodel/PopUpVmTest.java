package com.example.tourplanner.viewmodel;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PopUpVmTest {


    @Test
    void addTour(){
        PopUpVM popUpVM = new PopUpVM();

        popUpVM.getTourNameInput().set("tourNumeroTres");
        popUpVM.getTourDescriptionInput().set("tourDescription");
        popUpVM.getFromInput().set("Vienna");
        popUpVM.getToInput().set("Graz");
        popUpVM.getTransportTypeInput().set("Train");


        ToursOverviewVM toursOverviewVM = new ToursOverviewVM();
        int tourListSize = toursOverviewVM.getObservableTours().size();
        popUpVM.addTour();
        int newtourListSize=toursOverviewVM.getObservableTours().size();

        assertEquals(tourListSize+1,newtourListSize);

    }




}
