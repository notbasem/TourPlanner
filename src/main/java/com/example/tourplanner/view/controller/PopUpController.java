package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.PopUpVM;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


public class PopUpController implements Initializable{
    private PopUpVM popUpViewModel;

    public PopUpController() {

    }

    public PopUpController(PopUpVM popUpVIewModel) {
        this.popUpViewModel = new PopUpVM();
    }

    @FXML public TextField tourname;
    @FXML public TextArea tourDescription;
    @FXML public TextField from;
    @FXML public TextField to;
    @FXML public TextField transportType;
    @FXML public Label error;
    @FXML Button closeButton;

    @FXML
    public void closePopUp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        if (validateInput()) {
            popUpViewModel.addTour();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourname.textProperty().bindBidirectional(popUpViewModel.getTourNameInput());
        tourDescription.textProperty().bindBidirectional(popUpViewModel.getTourDescriptionInput());
        from.textProperty().bindBidirectional(popUpViewModel.getFromInput());
        to.textProperty().bindBidirectional(popUpViewModel.getToInput());
        transportType.textProperty().bindBidirectional(popUpViewModel.getTransportTypeInput());

        // Disable Button if Textfields not set
        closeButton.disableProperty().bind(tourname.textProperty().isEmpty()
                .or(tourDescription.textProperty().isEmpty())
                .or(from.textProperty().isEmpty())
                .or(to.textProperty().isEmpty())
                .or(transportType.textProperty().isEmpty())
        );
    }

    // also checks for blank input, not just empty
    private boolean validateInput() {
        List<TextInputControl> textFields = Arrays.asList(tourname, tourDescription, from, to, transportType);
        for (TextInputControl textField : textFields) {
            if (textField.getText() == null || textField.getText().isBlank()) {
                error.setText("Required field(s) is/are empty");
                return false;
            }
        }
        return true;
    }

    /*
    private boolean validateTextField(TextInputControl textInputControl) {
        AtomicBoolean valid = new AtomicBoolean(true);
        textInputControl.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(textInputControl.getText() == null || !textInputControl.getText().matches("[\\w\\s\\d!?#.'\\-öäüß]*?")){
                    // Wrong input
                    error.setText("Required textfield " + textInputControl.getPromptText() + " is empty");
                    valid.set(false);
                } else {
                    error.setText("");
                    valid.set(true);
                }
            }
        });
        return valid.get();
    }
    */
}
