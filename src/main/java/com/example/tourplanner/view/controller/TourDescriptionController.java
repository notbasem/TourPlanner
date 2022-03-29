package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TourDescriptionController implements Initializable {
    @FXML public TextField textField;


    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.textProperty().bindBidirectional(tourDescriptionViewModel.nameProperty());
    }
}
