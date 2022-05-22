package com.example.tourplanner.view.controller;

import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.viewmodel.TourDescriptionVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class TourDescriptionController implements Initializable, EventListener {
    @FXML public TextField titleInput;
    @FXML public TextArea descriptionInput;
    @FXML public TextField fromInput;
    @FXML public TextField toInput;
    @FXML public TextField transportInput;
    @FXML public TextField distanceInput;
    @FXML public TextField timeInput;
    @FXML public ImageView imageView;
    @FXML public Button update;

    private TourDescriptionVM tourDescriptionViewModel;

    public TourDescriptionController(TourDescriptionVM tourDescriptionViewModel){
        this.tourDescriptionViewModel = tourDescriptionViewModel;
        TourManager.SelectTourEventInstance().addListener(this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update.setDisable(true);
        titleInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTitle());
        descriptionInput.textProperty().bindBidirectional(tourDescriptionViewModel.getDescription());
        fromInput.textProperty().bindBidirectional(tourDescriptionViewModel.getFrom());
        toInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTo());
        transportInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTransportType());
        // distanceInput.textProperty().bindBidirectional(tourDescriptionViewModel.getDistance());
        //https://stackoverflow.com/questions/20205145/javafx-how-to-show-read-only-text !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        distanceInput.setEditable(false);
        distanceInput.setMouseTransparent(false);
        distanceInput.setFocusTraversable(false);
        //timeInput.textProperty().bindBidirectional(tourDescriptionViewModel.getTime());

        timeInput.setEditable(false);
        timeInput.setMouseTransparent(false);
        timeInput.setFocusTraversable(false);

        // Bindings.bindBidirectional(this.imageView.imageProperty(), tourDescriptionViewModel.getImageProperty());
        // imageView.setImage(tourDescriptionViewModel.getImage());
        // title.setText(this.tourDescriptionViewModel.getTour().get().getRouteInformation());

    }


    @Override
    public void onEvent() {
        update.setDisable(false);
        titleInput.setText(this.tourDescriptionViewModel.getTour().get().getName());
        descriptionInput.setText(this.tourDescriptionViewModel.getTour().get().getTourDescription());
        fromInput.setText(this.tourDescriptionViewModel.getTour().get().getFrom());
        toInput.setText(this.tourDescriptionViewModel.getTour().get().getTo());
        transportInput.setText(tourDescriptionViewModel.getTour().get().getTransportType());
        distanceInput.setText(this.tourDescriptionViewModel.getTour().get().getTourDistance().toString());
        timeInput.setText(this.tourDescriptionViewModel.getTour().get().getEstimatedTime().toString());
        imageView.setImage(this.tourDescriptionViewModel.getImageProperty().get());
    }

    @Override
    public void onSearch() {

    }

    public void updateTour() {
        tourDescriptionViewModel.updateTour();
    }
}
