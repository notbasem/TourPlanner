package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;

import com.example.tourplanner.view.validation.InputValidation;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class TourDescriptionController implements Initializable {
    @FXML public TextField titleInput;
    @FXML public TextArea descriptionInput;
    @FXML public TextField fromInput;
    @FXML public TextField toInput;
    @FXML public TextField transportInput;
    @FXML public TextField distanceInput;
    @FXML public TextField timeInput;
    @FXML public ImageView imageView;
    @FXML public Button update;
    @FXML public Button export;
    Optional<Tour> tour;

    private TourDescriptionVM tourDescriptionViewModel;


    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Image exportimage = new Image("https://www.flaticon.com/free-icons/export");
        ImageView imageViewbutton = new ImageView(exportimage);
        export.setGraphic(imageViewbutton);
        export.setContentDisplay(ContentDisplay.TOP);
        imageViewbutton.fitWidthProperty().bind(export.widthProperty().divide(10));
        imageViewbutton.setPreserveRatio(true);
        //Important otherwise button will wrap to text + graphic size (no resizing on scaling).
        export.setMaxWidth(Double.MAX_VALUE);*/

        titleInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTitle());
        descriptionInput.textProperty().bindBidirectional(tourDescriptionViewModel.getDescription());
        fromInput.textProperty().bindBidirectional(tourDescriptionViewModel.getFrom());
        toInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTo());
        transportInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTransportType());
        distanceInput.textProperty().bindBidirectional(tourDescriptionViewModel.getDistance());
        timeInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTime());
        imageView.imageProperty().bindBidirectional(tourDescriptionViewModel.getImageProperty());




        distanceInput.setEditable(false);
        distanceInput.setMouseTransparent(false);
        distanceInput.setFocusTraversable(false);

        timeInput.setEditable(false);
        timeInput.setMouseTransparent(false);
        timeInput.setFocusTraversable(false);

        // Disable Button if Textfields not set
        update.disableProperty().bind(tourDescriptionViewModel.getDisableButton());
    }

    public void updateTour() {
        tourDescriptionViewModel.updateTour();
    }

    public void exportTour(){
        tourDescriptionViewModel.exportTour();
    }
}