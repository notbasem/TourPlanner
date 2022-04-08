package com.example.tourplanner.DAL.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TourDialog extends TextInputDialog {
    public TourDialog() {
        super();
        initialize();
    }

    private void initialize() {
        this.setTitle("NEW TOUR");
        this.setHeaderText("Please enter a name for your tour!");
    }
}
