package com.example.tourplanner.view.controller;

import com.example.tourplanner.view.validation.InputValidation;
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
    @FXML public TextField difficulty;
    @FXML public Label error;
    private TourLogsPopUpVM tourLogsPopUpVM;
    private InputValidation inputValidation = new InputValidation();

    public TourLogsPopUpController(TourLogsPopUpVM tourLogsPopUpVM) {
        this.tourLogsPopUpVM = new TourLogsPopUpVM();
    }

    public void addTourLog(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        if (validateInput()&&validateRatingandDifficutlyInput()) {
            tourLogsPopUpVM.addTourLog();
            stage.close();
        } else {
                logger.error("Entered Data not accepted");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //date.textProperty().bindBidirectional(tourLogsPopUpVM.getDateInput());
        duration.textProperty().bindBidirectional(tourLogsPopUpVM.getTourDurationInput());
        distance.textProperty().bindBidirectional(tourLogsPopUpVM.getDistanceInput());
        comment.textProperty().bindBidirectional(tourLogsPopUpVM.getCommentInput());
        rating.textProperty().bindBidirectional(tourLogsPopUpVM.getRatingInput());
        difficulty.textProperty().bindBidirectional(tourLogsPopUpVM.getDifficultyInput());


        // Disable Button if Textfields not set
        closeButton.disableProperty().bind(duration.textProperty().isEmpty()
                .or(distance.textProperty().isEmpty())
                .or(comment.textProperty().isEmpty())
                .or(rating.textProperty().isEmpty())
                .or(difficulty.textProperty().isEmpty())

        );
    }

    // also checks for blank input, not just empty
    private boolean validateInput() {
        List<TextInputControl> textFields = Arrays.asList(duration, distance, comment, rating,difficulty);
        for (TextInputControl textField : textFields) {
            if (textField.getText() == null || textField.getText().isBlank()) {
                error.setText("Required field(s) is/are empty");
                return false;
            }



        }

        return true;
    }

    private boolean validateRatingandDifficutlyInput()
    {
        if(!inputValidation.validateInt(rating.textProperty())){

            error.setText("Choose a number from 1 to 5 for Rating");
            return false;
        }

        if(!inputValidation.validateInt(difficulty.textProperty())){

            error.setText("Choose a number from 1 to 5 for Difficulty");
            return false;
        }

        return true;
    }





}
