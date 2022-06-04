package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.TourLogsEditPopUpVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TourLogsEditPopUpController implements Initializable {

    private final TourLogsEditPopUpVM tourLogsEditPopUpVM;

    public TourLogsEditPopUpController(TourLogsEditPopUpVM tourLogsEditPopUpVM){
        this.tourLogsEditPopUpVM = new TourLogsEditPopUpVM();
    }
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

    @FXML
    public void updateLog(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        tourLogsEditPopUpVM.updateLog();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        date.textProperty().bindBidirectional( tourLogsEditPopUpVM.getDateInput());
        duration.textProperty().bindBidirectional( tourLogsEditPopUpVM.getTourDurationInput());
        distance.textProperty().bindBidirectional(tourLogsEditPopUpVM.getDistanceInput());
        comment.textProperty().bindBidirectional( tourLogsEditPopUpVM.getCommentInput());
        rating.textProperty().bindBidirectional(tourLogsEditPopUpVM.getRatingInput());

        tourLogsEditPopUpVM.setData();

    }


}
