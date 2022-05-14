package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class TourDescriptionController implements Initializable  {
    @FXML
   public Label title;

    @FXML
    public TextArea desc;

    @FXML
    public TextArea from;

    @FXML
    public TextArea to;

    @FXML
    public TextArea transport;

    @FXML
    public TextArea distance;

    @FXML
    public TextArea time;

    @FXML
    public ImageView routeView;

    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @FXML
    public void displayTourInfo(){
        System.out.println("hi");
        String i = this.tourDescriptionViewModel.getTour().get().getRouteInformation();
      //  desc.setText(i);
        String link=  this.tourDescriptionViewModel.apiThingy().replaceAll(" ", "%20");

        System.out.println("LINK "+link);

        Image image = new Image(link);
        routeView.setImage(image);

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
        from.setText(i);
    }

    @FXML
    public void displayTo(){
        String i = this.tourDescriptionViewModel.getTour().get().getTo();
        to.setText(i);
    }

    public  Optional<Tour> getTour(String tourname){
        return this.tourDescriptionViewModel.displayTourData(tourname);
    }

    @FXML
    public void displayTransportType(){
        String i = this.tourDescriptionViewModel.getTour().get().getTransportType();
        transport.setText(i);
    }
    @FXML
    public void displayDistance(){
        Float i = this.tourDescriptionViewModel.getTour().get().getTourDistance();
        distance.setText(String.valueOf(i));
    }

    @FXML
    public void displayTime(){
        Integer i = this.tourDescriptionViewModel.getTour().get().getEstimatedTime();
        time.setText(String.valueOf(i));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
