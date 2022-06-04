package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.MenuVM;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController {
    private final MenuVM menuViewModel;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem closeButton;
    @FXML
    MenuItem exportButton;
    @FXML
    TextField searchTextField;

    public MenuController(MenuVM menuViewModel) {
        this.menuViewModel = new MenuVM();
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(menuViewModel.searchStringProperty());
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> menuViewModel.onSearch());
    }

    @FXML
    public void onExport() {menuViewModel.exportAllToursAndLogs();}

    public void onClose() {
        ((Stage) menuBar.getScene().getWindow()).close();
    }

}
