package com.example.tourplanner.view.controller;

import com.example.tourplanner.FXMLDI;
import com.example.tourplanner.viewmodel.TourLogsVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class TourLogsController implements Initializable {
    @FXML
    TableView tableView;

    private final TourLogsVM tourLogsViewModel;

    public TourLogsController(TourLogsVM tourLogsViewModel){
        this.tourLogsViewModel =  new TourLogsVM();
    }

    @FXML
    public void openTourLogsPopUp(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLDI.load("TourLogsPopUpWindow.fxml", Locale.ENGLISH);
            Scene scene = new Scene(root);
            stage.setTitle("TourLogsPopupWindow");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
      tableView.setItems(tourLogsViewModel.getObservableTours());
    }
}
