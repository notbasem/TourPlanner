package com.example.tourplanner.view.controller;

import com.example.tourplanner.FXMLDI;
import com.example.tourplanner.business.API.AddedTourEventListener;
import com.example.tourplanner.business.API.AddedTourManager;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.viewmodel.ToursOverviewVM;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ToursOverviewController implements Initializable, AddedTourEventListener {
    @FXML
    public ListView<Tour> tourlist;

    @FXML
    public VBox vBox;

    private final ToursOverviewVM toursOverviewViewModel;


    public ToursOverviewController(ToursOverviewVM toursOverviewViewModel) {
        this.toursOverviewViewModel = new ToursOverviewVM();
        //SelectedTourManager.ToursViewManager().addListener(this);
        AddedTourManager.getAddedTourManager().addListener(this);
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
        TourManager.SelectTourEventInstance().selectTour(tourName);

    }
    @FXML
    public void delTour()  {
       toursOverviewViewModel.deleteTour();
        this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      tourlist.itemsProperty().bindBidirectional(this.toursOverviewViewModel.getObservableToursProperty());

     //   this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
        tourlist.getSelectionModel().select(0);
        TourManager.SelectTourEventInstance().selectTour(tourlist.getSelectionModel().getSelectedItem().getName());
    }


    @Override
    public void onEvent() {

    }

    @Override
    public void onSearch() {
        List<Tour> tempTourList = this.toursOverviewViewModel.getObservableTours();
        String searchText = AddedTourManager.getAddedTourManager().getSearch();

        System.out.println("SearchText: " + searchText);
        if (searchText.isEmpty()) {
            this.tourlist.setItems(this.toursOverviewViewModel.getObservableTours());
        } else {
            tempTourList.removeAll(tempTourList.stream().filter(tour -> !tour.getName().contains(searchText)).toList());
            System.out.println(tempTourList);
            this.tourlist.setItems((ObservableList<Tour>) tempTourList);
        }
    }
}
