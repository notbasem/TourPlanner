package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.MainVM;
import javafx.fxml.FXML;

public class MainController {
    private final MainVM mainViewModel;
    @FXML private ToursOverviewController toursOverviewController;
    @FXML private TourDescriptionController tourDescriptionController;

    public MainController(MainVM mainViewModel){
        this.mainViewModel = mainViewModel;
    }

    public MainVM getMainViewModel() {
        return mainViewModel;
    }

    @FXML
    void initialize(){}
}