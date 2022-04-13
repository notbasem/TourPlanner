package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TourDescriptionController implements Initializable {
    @FXML
   public Label title;

    @FXML
    public TextArea desc;

    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @FXML
    public String displayTourInfo(String tourname){
        System.out.println("hi");
        Optional<Tour> tour= this.tourDescriptionViewModel.displayTourData(tourname);
        String i = tour.get().getRouteInformation();
        desc.setText(i);
        System.out.println(tour.get().getRouteInformation());
        return "success";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  textArea.setText("hgiiiiiiii");

    }
}
