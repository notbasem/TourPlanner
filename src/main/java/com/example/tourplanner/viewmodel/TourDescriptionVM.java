package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.Optional;

public class TourDescriptionVM implements EventListener {
    public TourDescriptionVM(){
        TourManager.SelectTourEventInstance().addListener(this);
    }
    private ObjectProperty<javafx.scene.image.Image> imageProperty = new SimpleObjectProperty<>();


    Optional<Tour> tour;


   // public ObjectProperty imageProperty(){return imageProperty();}

    public Optional<Tour> displayTourData(String tourname){

        Optional<Tour> tour = DAL.getInstance().tourDao().get(tourname);

        return tour;
    }

    public ObjectProperty<javafx.scene.image.Image> getImageProperty(){
        System.out.println("should get image of clicked tour");

        Image image = new Image(DAL.getInstance().tourDao().get(TourManager.SelectTourEventInstance().getSelectedTour()).get().getRouteInformation());

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
}
