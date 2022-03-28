package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.model.Tour;

public class DAL {


        private Dao<Tour> tourDao;

        private DAL() {
            tourDao = new TourDao();
        }

        //
        // Tours:
        //
        public Dao<Tour> tourDao() {
            return tourDao;
        }


        //
        // Singleton-Pattern for DAL with early-binding
        //
        private static DAL instance = new DAL();

        public static DAL getInstance() {
            return instance;
        }


}
