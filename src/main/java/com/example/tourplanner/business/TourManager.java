package com.example.tourplanner.business;

import com.example.tourplanner.DAL.dal.TourDao;
import com.example.tourplanner.DAL.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private static TourManager instance;
    private List<TourListener> listeners;
    //private TourDao tourDao;

    public static TourManager Instance() {
        if (instance == null) {
            instance = new TourManager();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.listeners = new ArrayList<>();
    }

    /*
    public void addTour(String name, String description) {
        tourDao.save(new Tour(name, description));
        fireToursChanged();
    }

    public List<Tour> getTours() {
        return tourDao.findAll();
    }
    */

    public void addTourListener(TourListener listener) {
        listeners.add(listener);
    }

    private void fireToursChanged(Tour tour) {
        listeners.forEach(l->l.listChanged(tour));
    }

    public List<TourListener> getListeners() {
        return this.listeners;
    }
}
