package com.example.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourPlannerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("TourDescription.fxml"));
        Parent root = FXMLDI.load("MainWindow.fxml", Locale.ENGLISH);
        Scene scene = new Scene(root);
        stage.setTitle("Tour Planner");
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}