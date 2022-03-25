package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourLogsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TourLogsController {
    @FXML
    TableView tableView;

    private final TourLogsViewModel tourLogsViewModel;

    public TourLogsController(TourLogsViewModel tourLogsViewModel){
        this.tourLogsViewModel =  new TourLogsViewModel();

    }
}
