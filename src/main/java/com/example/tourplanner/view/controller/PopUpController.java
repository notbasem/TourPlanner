package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.PopUpVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class PopUpController implements Initializable {
    private PopUpVM popUpViewModel;

    public PopUpController() {

    }
    public PopUpController(PopUpVM popUpVIewModel) {
        this.popUpViewModel = new PopUpVM();
    }

    @FXML
    public TextField tourname;

    @FXML
    public TextArea tourDescription;

    @FXML
    public TextField from;

    @FXML
    public TextField to;

    @FXML
    public TextField transportType;

    @FXML
    public TextField distance;

    @FXML
    public TextField estimatedTime;

    @FXML
    public TextField routeInformation;

    @FXML
    Button closeButton;

    @FXML
    public void closePopUp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        popUpViewModel.addTour();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourname.textProperty().bindBidirectional(popUpViewModel.tourNameInput());
    }

}
