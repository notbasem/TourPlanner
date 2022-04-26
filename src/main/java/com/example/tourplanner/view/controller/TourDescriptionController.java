package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TourDescriptionController implements Initializable {
    @FXML
    public TextField title;

    @FXML
    public TextArea desc;

    private Optional<Tour> tour;

    private final TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @FXML
    public void displayTourInfo(){
        System.out.println("hi");
        String i = this.tour.get().getRouteInformation();
        desc.setText(i);
        System.out.println(tour.get().getRouteInformation());
    }
    @FXML
    public void displayTourDescription(){
        String i = this.tour.get().getTourDescription();
        desc.setText(i);
    }

    @FXML
    public void displayFrom(){
        String i = this.tour.get().getFrom();
        desc.setText(i);
    }

    @FXML
    public void displayTo(){
        String i = this.tour.get().getTo();
        desc.setText(i);
    }

    /*
    public void setTour(String tourname){
        this.tour = this.tourDescriptionViewModel.displayTourData(tourname);
    }

    public  Optional<Tour> getTour(String tourname){
        return this.tourDescriptionViewModel.displayTourData(tourname);
    }
    */

    @FXML
    public void displayTransportType(){
        String i = this.tour.get().getTransportType();
        desc.setText(i);
    }
    @FXML
    public void displayDistance(){
        Float i = this.tour.get().getTourDistance();
        desc.setText(String.valueOf(i));
    }

    @FXML
    public void displayTime(){
        Integer i = this.tour.get().getEstimatedTime();
        desc.setText(String.valueOf(i));
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.textProperty().bindBidirectional(tourDescriptionViewModel.titleProperty());
    }
}
