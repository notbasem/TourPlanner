package com.example.tourplanner.view.dialog;

import javafx.scene.control.TextInputDialog;

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
