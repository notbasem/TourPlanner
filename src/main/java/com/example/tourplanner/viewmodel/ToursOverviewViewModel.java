package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewViewModel {
    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();
    public ToursOverviewViewModel(){}

}
