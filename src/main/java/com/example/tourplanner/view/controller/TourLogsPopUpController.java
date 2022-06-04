package com.example.tourplanner.view.controller;

import com.example.tourplanner.TourPlannerApplication;
import com.example.tourplanner.viewmodel.TourLogsPopUpVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TourLogsPopUpController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TourLogsPopUpController.class.getSimpleName());

    @FXML Button closeButton;
    @FXML public TextField date;
    @FXML public TextField duration;
    @FXML public TextField distance;
    @FXML public TextField comment;
    @FXML public TextField rating;
    @FXML public Label error;


    TourLogsPopUpVM tourLogsPopUpVM;

    public TourLogsPopUpController(TourLogsPopUpVM tourLogsPopUpVM) {
        this.tourLogsPopUpVM = tourLogsPopUpVM;
    }

    public void addTourLog(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        if (validateInput()) {
            tourLogsPopUpVM.addTourLog();
            stage.close();
        } else {
            logger.error("Required field(s) is/are empty");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.textProperty().bindBidirectional(tourLogsPopUpVM.getDateInput());
        duration.textProperty().bindBidirectional(tourLogsPopUpVM.getTourDurationInput());
        distance.textProperty().bindBidirectional(tourLogsPopUpVM.getDistanceInput());
        comment.textProperty().bindBidirectional(tourLogsPopUpVM.getCommentInput());
        rating.textProperty().bindBidirectional(tourLogsPopUpVM.getRatingInput());

        // Disable Button if Textfields not set
        closeButton.disableProperty().bind(date.textProperty().isEmpty()
                .or(duration.textProperty().isEmpty())
                .or(distance.textProperty().isEmpty())
                .or(comment.textProperty().isEmpty())
                .or(rating.textProperty().isEmpty())
        );
    }

    private boolean validateInput() {
        List<TextInputControl> textFields = Arrays.asList(date, duration, distance, comment, rating);
        for (TextInputControl textField : textFields) {
            if (textField.getText() == null || textField.getText().isBlank()) {
                error.setText("Required field(s) is/are empty");
                return false;
            }
        }
        return true;
    }
}
