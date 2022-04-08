package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TourDescriptionController implements Initializable {
    @FXML Label title;

    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
