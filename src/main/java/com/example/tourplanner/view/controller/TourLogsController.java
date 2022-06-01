package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.FXMLDI;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.viewmodel.TourLogsVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class TourLogsController implements Initializable {
    @FXML
    public  TableView <TourLog>tableView;
    @FXML
    public TableColumn <TourLog, String> logDate;
    @FXML
    public TableColumn <TourLog, String> logDuration;
    @FXML
    public TableColumn <TourLog, String> logDistance;
    @FXML
    public TableColumn <TourLog, String> logComment;
    @FXML
    public TableColumn <TourLog, String> logRating;


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


    @FXML
    public void openTourLogsEditPopUp(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLDI.load("TourLogsEditPopUp.fxml", Locale.ENGLISH);
            Scene scene = new Scene(root);
            stage.setTitle("TourLogsEditPopupWindow");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void onSelectedTourLog()  {
        TourLog selectedTourLog = tableView.getSelectionModel().getSelectedItem() ;

     int id = tourLogsViewModel.getIdOfTour(selectedTourLog);
        TourManager.SelectTourEventInstance().selectTourLog(id);

        System.out.println(selectedTourLog.toString());;
    }

    @FXML
    public void deleteTourLog(){
        tourLogsViewModel.deleteTourLog();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.itemsProperty().bindBidirectional( tourLogsViewModel.getObservableTours());

        logDate.setCellValueFactory(new PropertyValueFactory("date"));
        logDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        logDistance.setCellValueFactory(new PropertyValueFactory("distance"));
        logComment.setCellValueFactory(new PropertyValueFactory("comment"));
        logRating.setCellValueFactory(new PropertyValueFactory("rating"));

    }
}
