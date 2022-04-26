package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.model.Tour;

public class MainVM {
    private TourDescriptionVM tourDescriptionViewModel;
    private ToursOverviewVM toursOverviewViewModel;

    public MainVM(ToursOverviewVM toursOverviewViewModel, TourDescriptionVM tourDescriptionViewModel) {
        this.toursOverviewViewModel = toursOverviewViewModel;
        this.tourDescriptionViewModel = tourDescriptionViewModel;

       this.toursOverviewViewModel.addSelectionChangedListener(this::selectTour);
    }

    private void selectTour(Tour selectedTour) {
        System.out.println("HALLO");
        System.out.println(selectedTour.getName());
        tourDescriptionViewModel.setTour(selectedTour);
    }
}
