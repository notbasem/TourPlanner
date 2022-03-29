package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourLogsVM;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TourLogsController {
    @FXML
    TableView tableView;

    private final TourLogsVM tourLogsViewModel;

    public TourLogsController(TourLogsVM tourLogsViewModel){
        this.tourLogsViewModel =  new TourLogsVM();

    }
}
