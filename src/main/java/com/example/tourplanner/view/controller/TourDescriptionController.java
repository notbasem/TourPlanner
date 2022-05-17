package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class TourDescriptionController implements Initializable, EventListener {



    @FXML
   public Label title;

    @FXML
    public TextArea description;

    @FXML
    public TextField fromInput;

    @FXML
    public TextField toInput;

    @FXML
    public TextField distanceInput;

    @FXML
    public TextField timeInput;

    @FXML
    public ImageView imageView;

    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
        TourManager.SelectTourEventInstance().addListener(this);

    }

    @FXML
    public void displayTourInfo(){

        TourManager.UpdateTourRouteImage().fireEvent();

        Image image = new Image(this.tourDescriptionViewModel.getTour().get().getRouteInformation());

        if(image.isError()){
            System.out.println("Something's wring with the image");
        }
        imageView.setImage(image);

        System.out.println(this.tourDescriptionViewModel.getTour().get().getRouteInformation());
    }

    @FXML
    public void displayTourDescription(){
        String i = this.tourDescriptionViewModel.getTour().get().getTourDescription();
       description.setText(i);
    }

    @FXML
    public void displayFrom(){
        String i = this.tourDescriptionViewModel.getTour().get().getFrom();
       fromInput.setText(i);
    }

    @FXML
    public void displayTo(){
        String i = this.tourDescriptionViewModel.getTour().get().getTo();
        toInput.setText(i);
    }

    public  Optional<Tour> getTour(String tourname){
        return this.tourDescriptionViewModel.displayTourData(tourname);
    }

    @FXML
    public void displayTransportType(){
        String i = this.tourDescriptionViewModel.getTour().get().getTransportType();
       // transport.setText(i);
    }
    @FXML
    public void displayDistance(){
        Float i = this.tourDescriptionViewModel.getTour().get().getTourDistance();
       distanceInput.setText(String.valueOf(i));
    }

    @FXML
    public void displayTime(){
        Integer i = this.tourDescriptionViewModel.getTour().get().getEstimatedTime();
        timeInput.setText(String.valueOf(i));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  Bindings.bindBidirectional(this.imageView.imageProperty(), tourDescriptionViewModel.getImageProperty());

        //   imageView.setImage(tourDescriptionViewModel.getImage());
     //   title.setText(this.tourDescriptionViewModel.getTour().get().getRouteInformation());

    }


    @Override
    public void onEvent() {
        System.out.println("new image is being displayed");

        imageView.setImage(this.tourDescriptionViewModel.getImageProperty().get());

    }

    @Override
    public void onSearch() {

    }
}
