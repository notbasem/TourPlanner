package com.example.tourplanner.viewmodel;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.EventListener;
import com.example.tourplanner.business.TourManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ToursOverviewVM implements EventListener {
    private static final Logger logger = LogManager.getLogger(ToursOverviewVM.class.getSimpleName());

    public ToursOverviewVM() {
        TourManager.Instance().addListener(this);
    }

    private ObservableList<Tour> tours = FXCollections.observableArrayList();


    public Property<ObservableList<Tour>> getObservableToursProperty() {
        setToursList();
        Property<ObservableList<Tour>> tourslistProperty = new SimpleObjectProperty<>(tours);
        return tourslistProperty;
    }

    public void setToursList() {
        this.tours = (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    public ObservableList<Tour> getObservableTours() {
        return (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
    }

    @Override
    public void onAddedTour() {
        tours.setAll(DAL.getInstance().tourDao().getAll());
    }


    public void deleteTour() {
        DAL.getInstance().tourDao().delete(TourManager.Instance().getSelectedTour());
    }

    public void exportTours(Stage stage) {
        // Open Filechooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle eine Datei");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showSaveDialog(stage);

        // Write File
        try {
            FileWriter w = new FileWriter(file);
            // Get JSONArray through getObservableTours()
            w.write(toursToJson().toString(4));
            w.close();

            logger.info("Successfully exported tours to PDF: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Could not export to PDF");
            e.printStackTrace();
        }
    }

    public void importTours(Stage stage) {
        // Open Filechooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle eine Datei");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showOpenDialog(stage);
        logger.info("Importing tours from: " + file.getAbsolutePath());

        // Parse JSON-File and save to database
        List<Tour> tours = jsonToTours(file.getAbsolutePath());
        if (tours.isEmpty()) {
            logger.error("Error while parsing JSON-Data");
            return;
        }

        // Save tours to database and update view
        for (Tour tour : tours) {
            DAL.getInstance().tourDao().create(tour);
        }
        TourManager.Instance().fireAddedTourEvent();
    }

    private List<Tour> jsonToTours(String file) {
        List<Tour> tours = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
                json.append('\n');
            }

            JSONArray jsonArray = new JSONArray(json.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                Tour t = new Tour(
                        j.getString("Name"),
                        j.getString("Description"),
                        j.getString("From"),
                        j.getString("To"),
                        j.getString("Transport"),
                        j.getFloat("Distance"),
                        j.getString("Time"));
                tours.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tours;
    }

    private JSONArray toursToJson() {
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
        return jsonArray;
    }


    @Override
    public void onSearch() {
        List<Tour> tempTourList = this.getObservableTours();
        String searchText = TourManager.Instance().getSearch();

        if (searchText.isEmpty()) {
            tours.setAll(this.getObservableTours());
        } else {
            tempTourList.removeAll(tempTourList.stream().filter(tour -> !tour.getName().contains(searchText)).toList());
            tours.setAll(tempTourList);
        }
    }

    @Override
    public void updateTourLog() {
    }

    @Override
    public void onEvent() {
    }

    @Override
    public void onAddedTourLogEvent() {
    }
}
