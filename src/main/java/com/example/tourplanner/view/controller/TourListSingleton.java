/*package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.dal.DAL;
import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


    public class TourListSingleton {
        private static TourListSingleton instance;
        ObservableList<Tour> tourlist = FXCollections.observableArrayList();


        public static TourListSingleton getInstance() {
            if (instance == null)
                instance = new TourListSingleton();
            return instance;
        }

        public ObservableList<Tour> getTourlist() {
            if (tourlist == null) {
                tourlist = (ObservableList<Tour>) DAL.getInstance().tourDao().getAll();
            }
            return tourlist;
        }

        public void setTourList(ObservableList<Tour> all) {
            this.tourlist=all;

        }
    }
    */
