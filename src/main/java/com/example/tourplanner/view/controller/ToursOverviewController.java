package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.ToursOverviewVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToursOverviewController implements Initializable {
    @FXML
    public ListView<Tour> Tourlist;
    private final ToursOverviewVM toursOverviewViewModel;

    public ToursOverviewController(ToursOverviewVM toursOverviewViewModel){
        this.toursOverviewViewModel = new ToursOverviewVM();
    }

    public ToursOverviewVM getToursOverviewViewModel(){return toursOverviewViewModel;}

    @FXML
    public void onSearchClick() {
        System.out.println("Button clicked!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
