package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;

public class DAL {


        private Dao<Tour> tourDao;
        public Dao <TourLog> tourLogsDao;

        private DAL() {
            tourDao = new TourDao();
            tourLogsDao = new TourLogDao();
        }


        public Dao<Tour> tourDao() {
            return tourDao;
        }


         public Dao<TourLog> tourLogsDao() {
        return tourLogsDao;
    }


        private static DAL instance = new DAL();

        public static DAL getInstance() {
            return instance;
        }


}
