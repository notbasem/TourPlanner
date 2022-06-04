package com.example.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;


public class TourPlannerApplication extends Application {
    private static final Logger logger = LogManager.getLogger(TourPlannerApplication.class.getSimpleName());

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Started Application ...");
        Parent root = FXMLDI.load("MainWindow.fxml", Locale.ENGLISH);
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Tour Planner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}