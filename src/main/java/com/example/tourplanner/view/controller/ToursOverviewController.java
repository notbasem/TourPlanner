package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.ToursOverviewViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToursOverviewController implements Initializable {
    @FXML
    public ListView<Tour> Tourlist;
    private final ToursOverviewViewModel toursOverviewViewModel;

    public ToursOverviewController(ToursOverviewViewModel toursOverviewViewModel){
        this.toursOverviewViewModel = new ToursOverviewViewModel();
    }

    public ToursOverviewViewModel getToursOverviewViewModel(){return toursOverviewViewModel;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
