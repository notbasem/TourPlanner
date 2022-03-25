package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourDescriptionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TourDescriptionController {
    @FXML
    TextArea textArea;
    private final TourDescriptionViewModel tourDescriptionViewModel;

    public TourDescriptionController( TourDescriptionViewModel tourDescriptionViewModel){
        this.tourDescriptionViewModel = new TourDescriptionViewModel();

    }

}
