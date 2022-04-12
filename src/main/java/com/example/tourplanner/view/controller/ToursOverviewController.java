package com.example.tourplanner.view.controller;

import com.example.tourplanner.FXMLDI;
import com.example.tourplanner.view.dialog.TourDialog;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.ToursOverviewVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ToursOverviewController implements Initializable {
    @FXML
    public ListView<Tour> tourlist;

    @FXML
    public VBox vBox;

    private final ToursOverviewVM toursOverviewViewModel;


    public ToursOverviewController(ToursOverviewVM toursOverviewViewModel) {
        this.toursOverviewViewModel = new ToursOverviewVM();
    }

    public ToursOverviewVM getToursOverviewViewModel() {
        return toursOverviewViewModel;
    }

    @FXML
    public void openPopUp(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLDI.load("PopUpWindow.fxml", Locale.ENGLISH);
            Scene scene = new Scene(root);
            stage.setTitle("PopupWindow");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onSelectedTour() {
        System.out.println("clicked on " + tourlist.getSelectionModel().getSelectedItem());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //   System.out.println(this.toursOverviewViewModel.getObservableTours());
        this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
    }
}
