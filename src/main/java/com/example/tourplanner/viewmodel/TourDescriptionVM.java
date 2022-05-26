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

import java.io.IOException;
import java.util.Optional;

@Getter
public class TourDescriptionVM implements EventListener {
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

    public TourDescriptionVM(){
        TourManager.SelectTourEventInstance().addListener(this);
    }


   // public ObjectProperty imageProperty(){return imageProperty();}

    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = DAL.getInstance().tourDao().get(tourname);

        return tour;
    }

    public ObjectProperty<Image> getImageProperty(){
        System.out.println("should get image of clicked tour");
        //Image image = new Image(DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour()).get().getRouteInformation());

        ApiConnection apiConnection = new ApiConnection();
        String link = updateLink(apiConnection, from.get(), to.get());
        // Image kann bilder schon automatisch im Backgroundthread laden/anzeigen
        Image image = new Image(link,
                640,     // width
                680,    // height
                true,   // preserve ratio
                true,  // smooth rescaling
                true   // load in background
        );

        if (image.isError())
            TourManager.UpdateTourRouteImage().fireEvent();

        imageProperty= new SimpleObjectProperty<Image>(image);
     //   System.out.println("i'm heere");
      //  On image = new ObjectProperty<Image>(DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour()).get().getRouteInformation());

      //  return image;
    return imageProperty;}

    @Override
    public void onEvent() {
       this.tour= DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour());
       System.out.println("event fired: Tour selected");
    }

    @Override
    public void onSearch() {

    }

    public Optional<Tour> getTour() {
        return tour;
    }

    public void updateTour() {
        Tour newTour = new Tour(title.get(), description.get(), from.get(), to.get(), transportType.get(), Float.parseFloat(distance.get()), Float.parseFloat(time.get()), tour.get().getRouteInformation());

        ApiConnection apiConnection = new ApiConnection();
        if (!from.get().equalsIgnoreCase(tour.get().getFrom()) || !to.get().equalsIgnoreCase(tour.get().getTo())) {
            String link = updateLink(apiConnection, from.get(), to.get());
            newTour.setTourDistance(apiConnection.getDistance());
            newTour.setEstimatedTime(apiConnection.getTime());
        }
        DAL.getInstance().tourDao().updateTour(tour.get(), newTour);
        tour = Optional.of(newTour);
        System.out.println("URL: " + imageProperty.get().getUrl());
        System.out.println("URL: " + getImageProperty().get().getUrl());
        TourManager.ToursViewManager().fireEvent();
    }

    public String updateLink(ApiConnection apiConnection, String from, String to) {
        try {
            return new ApiConnection().sendRequest(from,to).replaceAll(" ", "%20");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
