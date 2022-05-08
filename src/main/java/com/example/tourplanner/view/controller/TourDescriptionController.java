package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class TourDescriptionController implements Initializable  {
    @FXML
   public Label title;

    @FXML
    public TextArea desc;


    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @FXML
    public void displayTourInfo(){
        System.out.println("hi");
        String i = this.tourDescriptionViewModel.getTour().get().getRouteInformation();
        desc.setText(i);
        this.tourDescriptionViewModel.apiThingy();

        System.out.println(this.tourDescriptionViewModel.getTour().get().getRouteInformation());
    }

    @FXML
    public void displayTourDescription(){
        String i = this.tourDescriptionViewModel.getTour().get().getTourDescription();
        desc.setText(i);
    }

    @FXML
    public void displayFrom(){
        String i = this.tourDescriptionViewModel.getTour().get().getFrom();
        desc.setText(i);
    }

    @FXML
    public void displayTo(){
        String i = this.tourDescriptionViewModel.getTour().get().getTo();
        desc.setText(i);
    }

    public  Optional<Tour> getTour(String tourname){
        return this.tourDescriptionViewModel.displayTourData(tourname);
    }

    @FXML
    public void displayTransportType(){
        String i = this.tourDescriptionViewModel.getTour().get().getTransportType();
        desc.setText(i);
    }
    @FXML
    public void displayDistance(){
        Float i = this.tourDescriptionViewModel.getTour().get().getTourDistance();
        desc.setText(String.valueOf(i));
    }

    @FXML
    public void displayTime(){
        Integer i = this.tourDescriptionViewModel.getTour().get().getEstimatedTime();
        desc.setText(String.valueOf(i));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
