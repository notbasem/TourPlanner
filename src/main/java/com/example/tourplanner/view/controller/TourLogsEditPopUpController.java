package com.example.tourplanner.view.controller;

import com.example.tourplanner.view.validation.InputValidation;
import com.example.tourplanner.viewmodel.TourLogsEditPopUpVM;
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

public class TourLogsEditPopUpController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TourLogsPopUpController.class.getSimpleName());
    @FXML Button closeButton;
    @FXML public TextField date;
    @FXML public TextField duration;
    @FXML public TextField distance;
    @FXML public TextField comment;
    @FXML public TextField rating;
    @FXML public TextField difficulty;
    @FXML public Label error;

    private final TourLogsEditPopUpVM tourLogsEditPopUpVM;
    private InputValidation inputValidation = new InputValidation();

    public TourLogsEditPopUpController(TourLogsEditPopUpVM tourLogsEditPopUpVM){
        this.tourLogsEditPopUpVM = new TourLogsEditPopUpVM();
    }

    @FXML
    public void updateLog(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        if (validateInput()&&validateRatingandDifficutlyInput()) {
            tourLogsEditPopUpVM.updateLog();
            stage.close();
        } else {
            logger.error("Entered Data not accepted");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.textProperty().bindBidirectional(tourLogsEditPopUpVM.getDateInput());
        duration.textProperty().bindBidirectional( tourLogsEditPopUpVM.getTourDurationInput());
        distance.textProperty().bindBidirectional(tourLogsEditPopUpVM.getDistanceInput());
        comment.textProperty().bindBidirectional( tourLogsEditPopUpVM.getCommentInput());
        rating.textProperty().bindBidirectional(tourLogsEditPopUpVM.getRatingInput());
        difficulty.textProperty().bindBidirectional(tourLogsEditPopUpVM.getDifficultyInput());

        tourLogsEditPopUpVM.setData();
        date.setEditable(false);

        // Disable Button if Textfields not set
        closeButton.disableProperty().bind(
                duration.textProperty().isEmpty()
                .or(distance.textProperty().isEmpty())
                .or(comment.textProperty().isEmpty())
                .or(rating.textProperty().isEmpty())
        );



    }

    // also checks for blank input, not just empty
    public boolean validateInput() {
        List<TextInputControl> textFields = Arrays.asList(date, duration, distance, comment, rating, difficulty);
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
