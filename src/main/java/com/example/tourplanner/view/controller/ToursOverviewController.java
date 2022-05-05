package com.example.tourplanner.view.controller;

import com.example.tourplanner.FXMLDI;
import com.example.tourplanner.business.TourManager;
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
    public void onSelectedTour() throws IOException {
        String tourName = String.valueOf(tourlist.getSelectionModel().getSelectedItem());
        TourManager.Instance().selectTour(tourName);

    }
    @FXML
    public void delTour() throws IOException {
       toursOverviewViewModel.deleteTour();
        for (Tour tour:TourListSingleton.getInstance().getTourlist()) {
            if(tour.getName()==TourManager.Instance().getSelectedTour()){
                TourListSingleton.getInstance().getTourlist().remove(tour);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
    }
}
