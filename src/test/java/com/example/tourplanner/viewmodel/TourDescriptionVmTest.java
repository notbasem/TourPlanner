package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.Managers.TourManager;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;

public class TourDescriptionVmTest {

/*
    @Test
    public void updateTourTest(){
        PopUpVM popUpVM = new PopUpVM();

        popUpVM.getTourNameInput().set("tourName");
        popUpVM.getTourDescriptionInput().set("tourDescription");
        popUpVM.getFromInput().set("Vienna");
        popUpVM.getToInput().set("Graz");
        popUpVM.getTransportTypeInput().set("Train");

        popUpVM.addTour();

       String oldtour =DAL.getInstance().tourDao().get("tourName").get().getTourDescription();
        System.out.println("old"+oldtour);
        TourManager.Instance().selectTour("tourName");

        TourDescriptionVM tourDescriptionVM = new TourDescriptionVM();

        tourDescriptionVM.getTitle().set("tourName");
        tourDescriptionVM.getDescription().set("UpdatedtourDescription");
        tourDescriptionVM.getFrom().set("Vienna");
        tourDescriptionVM.getTo().set("Linz");
        tourDescriptionVM.getTransportType().set("Train");

        tourDescriptionVM.updateTour();

   String newtour = DAL.getInstance().tourDao().get("tourName").get().getTourDescription();
        System.out.println("new"+newtour);

        assertNotEquals(oldtour,newtour);
    }*/
}
