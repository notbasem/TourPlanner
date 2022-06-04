package com.example.tourplanner.view.factory;

import com.example.tourplanner.view.controller.*;
import com.example.tourplanner.viewmodel.*;

//Controller Factory makes a Singleton out of every Controller --> Only one object of each Controller class exists throughout the entire project
public class ControllerFactory {
    private final MainVM mainViewModel;
    private final ToursOverviewVM toursOverviewViewModel;
    private final TourDescriptionVM tourDescriptionViewModel;
    private final MenuVM menuViewModel;
    private final TourLogsVM tourLogsViewModel;
    private final PopUpVM popUpViewModel;
    private final TourLogsPopUpVM tourLogsPopUpVM;
    private final TourLogsEditPopUpVM tourLogsEditPopUpVM;

    public ControllerFactory(){
        this.toursOverviewViewModel = new ToursOverviewVM();
        this.tourDescriptionViewModel = new TourDescriptionVM();
        this.menuViewModel = new MenuVM();
        this.tourLogsViewModel = new TourLogsVM();
        this.mainViewModel = new MainVM(toursOverviewViewModel, tourDescriptionViewModel);
        this.popUpViewModel = new PopUpVM();
        this.tourLogsPopUpVM = new TourLogsPopUpVM();
        this.tourLogsEditPopUpVM = new TourLogsEditPopUpVM();

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

        }else if (controllerClass == PopUpController.class) {
            return new PopUpController(popUpViewModel);

        }
        else if (controllerClass == MenuController.class) {
            return new MenuController(menuViewModel);
        }
        else if (controllerClass == TourLogsPopUpController.class) {
            return new TourLogsPopUpController(tourLogsPopUpVM);
        }
        else if (controllerClass == TourLogsEditPopUpController.class) {
            return new TourLogsEditPopUpController(tourLogsEditPopUpVM);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance(){return instance;}

}
