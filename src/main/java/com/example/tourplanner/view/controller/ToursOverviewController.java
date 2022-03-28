package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.ToursOverviewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ToursOverviewController {
    @FXML
    public ListView<Tour> Tourlist;
    private final ToursOverviewViewModel toursOverviewViewModel;

    public ToursOverviewController(ToursOverviewViewModel toursOverviewViewModel){
        this.toursOverviewViewModel = new ToursOverviewViewModel();
    }
}
