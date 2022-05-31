package com.example.tourplanner;

import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;


public class TourPlannerApplication extends Application {
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    @Override
    public void start(Stage stage) throws IOException {
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