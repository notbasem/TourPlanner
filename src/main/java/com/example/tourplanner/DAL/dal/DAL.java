package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.Managers.TourManager;

public class DAL {
        private static DAL instance;
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





        public static DAL getInstance() {
            if (instance == null) {
                instance = new DAL();
            }
            return instance;
        }


}
