package com.example.tourplanner.viewmodel;
import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


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
        tours.setAll( DAL.getInstance().tourDao().getAll());
    }


    public void deleteTour(){
        DAL.getInstance().tourDao().delete(TourManager.SelectTourEventInstance().getSelectedTour());
    }


    @Override
    public void onSearch() {
        List<Tour> tempTourList = this.getObservableTours();
        String searchText =TourManager.SelectTourEventInstance().getSearch();

        System.out.println("SearchText: " + searchText);
        if (searchText.isEmpty()) {
            tours.setAll(this.getObservableTours());
        } else {
            tempTourList.removeAll(tempTourList.stream().filter(tour -> !tour.getName().contains(searchText)).toList());
            System.out.println(tempTourList);
            tours.setAll((ObservableList<Tour>) tempTourList);
        }
    }

    @Override
    public void updateTourLog() {}

    @Override
    public void onEvent() {}

    @Override
    public void onAddedTourLogEvent() {}


}
