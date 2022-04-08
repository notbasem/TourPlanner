package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.dialog.TourDialog;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.ToursOverviewVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
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
    public void addTour() {
        System.out.println("Show Tour Dialog!");
        TourDialog td = new TourDialog();
        td.showAndWait();
        toursOverviewViewModel.addTour(td.getResult());
    }

    @FXML
    public void onSelectedTour() {
        System.out.println("clicked on " + tourlist.getSelectionModel().getSelectedItem());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(this.toursOverviewViewModel.getObservableTours());
        this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
    }
}
