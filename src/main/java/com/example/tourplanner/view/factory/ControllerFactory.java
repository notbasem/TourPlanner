package com.example.tourplanner.view.factory;

import com.example.tourplanner.view.controller.*;
import com.example.tourplanner.viewmodel.*;

//Controller Factory makes a Singleton out of every Controller --> Only one object of each Controller class exists throughout the entire project
public class ControllerFactory {
    private final MainViewModel mainViewModel;
    private final ToursOverviewViewModel toursOverviewViewModel;
    private final TourDescriptionViewModel tourDescriptionViewModel;
    private final MenuViewModel menuViewModel;
    private final TourLogsViewModel tourLogsViewModel;


    public ControllerFactory(){
        mainViewModel = new MainViewModel();
        toursOverviewViewModel = new ToursOverviewViewModel();
        tourDescriptionViewModel = new TourDescriptionViewModel();
        menuViewModel = new MenuViewModel();
        tourLogsViewModel = new TourLogsViewModel();

    }

    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainController.class) {
            return new MainController(mainViewModel);
        } else if (controllerClass == ToursOverviewController.class) {
            return new ToursOverviewController(toursOverviewViewModel);

        }else if (controllerClass == TourDescriptionController.class) {
            return new TourDescriptionController(tourDescriptionViewModel);

        }else if (controllerClass == TourLogsController.class) {
            return new TourLogsController(tourLogsViewModel);

        }else if (controllerClass == MenuController.class) {
            return new MenuController(menuViewModel);

        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance(){return instance;}

}
