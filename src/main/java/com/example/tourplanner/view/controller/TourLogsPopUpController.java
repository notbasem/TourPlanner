package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourLogsPopUpVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;




public class TourLogsPopUpController implements Initializable {
    @FXML
    Button closeButton;

    @FXML
    public TextField date;

    @FXML
    public TextField duration;

    @FXML
    public TextField distance;

    @FXML
    public TextField comment;

    @FXML
    public TextField rating;



    TourLogsPopUpVM tourLogsPopUpVM;
    public TourLogsPopUpController(TourLogsPopUpVM tourLogsPopUpVM) {
        this.tourLogsPopUpVM = tourLogsPopUpVM;
    }

    public void addTourLog(ActionEvent event) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            tourLogsPopUpVM.addTourLog();
            stage.close();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("hfydj");
        date.textProperty().bindBidirectional( tourLogsPopUpVM.getDateInput());
        duration.textProperty().bindBidirectional( tourLogsPopUpVM.getTourDurationInput());
        distance.textProperty().bindBidirectional(tourLogsPopUpVM.getDistanceInput());
        comment.textProperty().bindBidirectional( tourLogsPopUpVM.getCommentInput());
        rating.textProperty().bindBidirectional(tourLogsPopUpVM.getRatingInput());
    }
}
