package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ToursOverviewVM implements EventListener {

    public ToursOverviewVM(){TourManager.SelectTourEventInstance().addListener(this);}
    private ObservableList<Tour> tours = FXCollections.observableArrayList();


    public Property<ObservableList<Tour>>getObservableToursProperty() {
        setToursList();
        Property<ObservableList<Tour>> tourslistProperty = new SimpleObjectProperty<>(tours);
        return tourslistProperty;
    }


    public void setToursList() {
        this.tours= (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public ObservableList<Tour> getObservableTours() {
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    @Override
    public void onAddedTour() {
        System.out.println("Why are you not working");
        tours.setAll( DAL.getInstance().tourDao().getAll());
    }


    public void deleteTour(){
        DAL.getInstance().tourDao().delete(TourManager.SelectTourEventInstance().getSelectedTour());
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onAddedTourLogEvent() {

    }

    @Override
    public void onSearch() {

    }

    @Override
    public void updateTourLog() {

    }

}
