package com.example.tourplanner.viewmodel;

public class MainVM {
    TourDescriptionVM tourDescriptionViewModel;
    ToursOverviewVM toursOverviewViewModel;

    public MainVM(ToursOverviewVM toursOverviewViewModel, TourDescriptionVM tourDescriptionViewModel) {
        this.toursOverviewViewModel = toursOverviewViewModel;
        this.tourDescriptionViewModel = tourDescriptionViewModel;
    }
}
