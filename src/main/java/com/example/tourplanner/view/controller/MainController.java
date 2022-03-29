package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.MainVM;

public class MainController {
    private MainVM mainViewModel;
    private ToursOverviewController toursOverviewController;
    private TourDescriptionController tourDescriptionController;

    public MainController(MainVM mainViewModel){
        this.mainViewModel = mainViewModel;
    }
}