package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import lombok.Getter;

import java.util.Optional;

@Getter
public class TourDescriptionVM implements EventListener {
    private Optional<Tour> tour;
    private boolean update = true;
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    // private final StringProperty routeInformation = new SimpleStringProperty();
    private ObjectProperty<javafx.scene.image.Image> imageProperty = new SimpleObjectProperty<>();

    public TourDescriptionVM() {
        TourManager.SelectTourEventInstance().addListener(this);
        TourManager.ToursViewManager().addListener(this);
    }


    public Optional<Tour> displayTourData(String tourname) {

        Optional<Tour> tour = DAL.getInstance().tourDao().get(tourname);

        return tour;
    }

    public ObjectProperty<Image> getImageProperty() {
        System.out.println("should get image of clicked tour");
        String link = updateLink(from.get(), to.get());
        System.out.println("LINK: " + link);
        // Image kann bilder schon automatisch im Backgroundthread laden/anzeigen
        Image image = new Image(link,
                640,     // width
                680,    // height
                true,   // preserve ratio
                true,  // smooth rescaling
                true   // load in background
        );
        imageProperty.setValue(image);
        return imageProperty;
    }

    private void updateImageProperty(String map) {
        // Image kann bilder schon automatisch im Backgroundthread laden/anzeigen
        Image image = new Image(map,
                640,     // width
                680,    // height
                true,   // preserve ratio
                true,  // smooth rescaling
                true   // load in background
        );
        imageProperty.setValue(image);
    }

    @Override
    public void onEvent() {
        this.tour = DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour());
        System.out.println("event fired: Tour selected " + tour.get().tourToString());
        if (tour.isPresent()) {
            title.setValue(tour.get().getName());
            description.setValue(tour.get().getTourDescription());
            from.setValue(tour.get().getFrom());
            to.setValue(tour.get().getTo());
            transportType.setValue(tour.get().getTransportType());
            distance.setValue(tour.get().getTourDistance().toString());
            time.setValue(tour.get().getEstimatedTime());
            imageProperty.setValue(getImageProperty().get());
        }
        update = false;
    }

    @Override
    public void onSearch() {

    }

    public Optional<Tour> getTour() {
        return tour;
    }

    public void updateTour() {
        Tour newTour = new Tour(title.get(), description.get(), from.get(), to.get(), transportType.get(), Float.parseFloat(distance.get()), time.get());
        ApiConnection apiConnection = new ApiConnection(from.get(), to.get());
        System.out.println("Old Distance: " + newTour.getTourDistance() + " | New Distance: " + apiConnection.getDistance());

        newTour.setTourDistance(apiConnection.getDistance());
        newTour.setEstimatedTime(apiConnection.getTime());
        updateImageProperty(apiConnection.getMap().getMapString());
        DAL.getInstance().tourDao().updateTour(tour.get(), newTour);
        System.out.println("URL: " + imageProperty.get().getUrl());
        TourManager.ToursViewManager().fireEvent();
    }

    public String updateLink(String from, String to) {
        ApiConnection apiConnection = new ApiConnection(from, to);
        return (apiConnection.getMap().getMapString()).replaceAll(" ", "%20");
    }
}
