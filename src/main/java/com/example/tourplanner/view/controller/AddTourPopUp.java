package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddTourPopUp {
    private TextField tourname, tourDescription, from, to, transportType, distance, estimatedTime, routeInformation;

    public Tour display() {
        final Stage  window = new Stage();
        BorderPane bP = new BorderPane();

        window.setTitle("Add Tour");
        Button closeButton = new Button("Add Tour");

        closeButton.setOnAction(e -> {
            window.close();
            addTour();
        });

        VBox vbox = new VBox(tourname,  tourDescription, from, to, transportType, distance, estimatedTime, routeInformation);
        vbox.getChildren().addAll();


        bP.setCenter(vbox);
        Scene scene = new Scene(bP, 800,800);

        window.setScene(scene);
        window.show();
        return addTour();
    }

    private Tour addTour() {
        if(tourname.getText().isEmpty()) {
            return null;
        }
        else{
            float distance = Float.parseFloat(this.distance.getText());
            int estimatedTime;

            if(this.estimatedTime.getText().matches("\\d+")){
                estimatedTime = Integer.parseInt(this.estimatedTime.getText());
                System.out.println("It is an integer");
            }else{
                estimatedTime = -1;
                System.out.println("not an integer");
            }
            Tour tour = new Tour(tourname.getText(), tourDescription.getText(), from.getText(), to.getText(), transportType.getText(), distance, estimatedTime, routeInformation.getText());
            return tour;
        }
    }
}
