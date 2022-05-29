package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
//import com.example.tourplanner.view.controller.TourListSingleton;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class ToursOverviewVM {

    public ToursOverviewVM() {
    }

    public ObservableList<Tour> getObservableTours() {
        //TourListSingleton.getInstance().setTourList((ObservableList<Tour>) DAL.getInstance().tourDao().getAll()) ;
        // return TourListSingleton.getInstance().getTourlist();
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public void deleteTour() {
        DAL.getInstance().tourDao().delete(TourManager.SelectTourEventInstance().getSelectedTour());
    }

    public void exportTours() {
        try {
            File myObj = new File("export.json");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter w = new FileWriter("export.json");
            JSONArray jsonArray = new JSONArray();
            for (Tour tour : getObservableTours()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Name", tour.getName());
                jsonObject.put("From", tour.getFrom());
                jsonObject.put("To", tour.getTo());
                jsonObject.put("Transport", tour.getTransportType());
                jsonObject.put("Distance", tour.getTourDistance());
                jsonObject.put("Time", tour.getEstimatedTime());
                jsonObject.put("Description", tour.getTourDescription());
                jsonArray.put(jsonObject);
            }
            w.write(jsonArray.toString(4));
            w.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
