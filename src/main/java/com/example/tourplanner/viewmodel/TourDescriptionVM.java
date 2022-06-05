package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.PdfManager;
import com.example.tourplanner.business.TourManager;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Getter
public class TourDescriptionVM implements EventListener {
    private static final Logger logger = LogManager.getLogger(TourDescriptionVM.class.getSimpleName());
    private Optional<Tour> tour;
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
        TourManager.Instance().addListener(this);
        //  AddedTourManager.getAddedTourManager().addListener(this);
    }

    public ObjectProperty<Image> getImageProperty() {
        if (this.title.get() == null){
            imageProperty.setValue(null);
        } else {
            logger.info("Retrieve image from");
            String link = updateLink(from.get(), to.get());
            logger.info("New image-link: " + link);
            // Image kann bilder schon automatisch im Backgroundthread laden/anzeigen
            Image image = new Image(link,
                    640,     // width
                    680,    // height
                    true,   // preserve ratio
                    true,  // smooth rescaling
                    true   // load in background
            );
            imageProperty.setValue(image);
        }
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
        this.tour = DAL.getInstance().tourDao().get(TourManager.Instance().getSelectedTour());
        logger.info("Selected tour: " + tour.get().getName());
        title.setValue(tour.get().getName());
        description.setValue(tour.get().getTourDescription());
        from.setValue(tour.get().getFrom());
        to.setValue(tour.get().getTo());
        transportType.setValue(tour.get().getTransportType());
        distance.setValue(tour.get().getTourDistance().toString());
        time.setValue(tour.get().getEstimatedTime());
        imageProperty.setValue(getImageProperty().get());
    }

    public Optional<Tour> getTour() {
        return tour;
    }

    public void updateTour() {
        Tour newTour = new Tour(title.get(), description.get(), from.get(), to.get(), transportType.get(), Float.parseFloat(distance.get()), time.get());
        ApiConnection apiConnection = new ApiConnection(from.get(), to.get());
        logger.info("Updated distance from: " + newTour.getTourDistance() + " to: " + apiConnection.getDistance());
        newTour.setTourDistance(apiConnection.getDistance());
        newTour.setEstimatedTime(apiConnection.getTime());
        updateImageProperty(apiConnection.getMap().getMapString());
        DAL.getInstance().tourDao().update(tour.get(), newTour);
        TourManager.Instance().fireAddedTourEvent();
    }

    @Override
    public void onSearch() {
    }

    @Override
    public void updateTourLog() {
    }

    @Override
    public void onAddedTour() {
    }

    @Override
    public void onAddedTourLogEvent() {
    }

    public String updateLink(String from, String to) {
        ApiConnection apiConnection = new ApiConnection(from, to);
        return (apiConnection.getMap().getMapString()).replaceAll(" ", "%20");
    }

    public BooleanBinding getDisableButton() {
        if (title.get() == null) {
            logger.error("Tour is not set");
            return title.isEmpty()
                    .or(from.isEmpty())
                    .or(to.isEmpty())
                    .or(transportType.isEmpty())
                    .or(distance.isEmpty())
                    .or(transportType.isEmpty())
                    .or(description.isEmpty());
        }
        return (title.isEmpty()
                .or(from.isEmpty())
                .or(to.isEmpty())
                .or(transportType.isEmpty())
                .or(description.isEmpty()))
                .or((title.isNotEqualTo(tour.get().getName())
                        .or(from.isNotEqualTo(tour.get().getFrom()))
                        .or(to.isNotEqualTo(tour.get().getTo()))
                        .or(transportType.isNotEqualTo(tour.get().getTransportType()))
                        .or(description.isNotEqualTo(tour.get().getTourDescription()))
                ).not());
    }

    public void exportTour() {
        Optional<Tour> selectedTour = DAL.getInstance().tourDao().get(TourManager.Instance().getSelectedTour());

       List<TourLog> tourLogsList = DAL.getInstance().tourLogsDao.getlogs(TourManager.Instance().getSelectedTour());
        System.out.println(tourLogsList);

        PdfManager.getInstance().exportSelectedTour(selectedTour, tourLogsList);

    }
}
