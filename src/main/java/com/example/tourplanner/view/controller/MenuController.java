package com.example.tourplanner.view.controller;

import com.example.tourplanner.viewmodel.MenuViewModel;
import javafx.scene.control.Menu;

public class MenuController {

    private final MenuViewModel menuViewModel;

    public MenuController (MenuViewModel menuViewModel)
    {
        this.menuViewModel = new MenuViewModel() ;
    }
}
