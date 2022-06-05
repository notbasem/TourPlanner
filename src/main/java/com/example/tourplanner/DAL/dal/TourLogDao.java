package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.DbConnection;
import com.example.tourplanner.DAL.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog> {
    private static final Logger logger = LogManager.getLogger(TourLogDao.class.getSimpleName());

    public ObservableList<TourLog> getlogs(String name) {
        ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tour_id, date, duration,distance,comment, rating
                FROM tourlogs WHERE tourname =?
                """)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tourLogs.add(new TourLog(resultSet.getInt(1),name, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6)));
            }
            logger.info("TourLog read successfully");
        } catch (SQLException e) {
            logger.error("TourLog read successfully");
            e.printStackTrace();
        }
        return tourLogs;
    }

    @Override
    public Optional<TourLog> get(String name) {
        return Optional.empty();
    }

    public ObservableList<TourLog> getAll() {
        ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tour_id, tourname, date, duration, distance, comment, rating From tourlogs
                """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tourLogs.add(new TourLog(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tourLogs;

    }


    public void create(TourLog tourLog) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                INSERT INTO tourlogs
                (tourname, date, duration, distance, comment, rating)
                VALUES (?, ?, ?, ?, ?, ?);
                """)) {
            statement.setString(1, tourLog.getTourname());
            statement.setString(2, tourLog.getDate());
            statement.setString(3, tourLog.getDuration());
            statement.setInt(4, tourLog.getDistance());
            statement.setString(5, tourLog.getComment());
            statement.setInt(6, tourLog.getRating());
            statement.execute();
            logger.info("TourLog created successfully");
        } catch (SQLException throwables) {
            logger.error("TourLog could not be created");
            throwables.printStackTrace();
        }


    }

    public void update(TourLog oldTourLog, TourLog newTourLog) {
        int tourid = getLogId(oldTourLog, oldTourLog.getTourname());

        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE tourlogs
                SET tourname=?,  date=?,  duration=?, distance=?, comment=?, rating=?
                WHERE tour_id=? ;
                """)) {
            statement.setString(1, newTourLog.getTourname());
            statement.setString(2, newTourLog.getDate());
            statement.setString(3, newTourLog.getDuration());
            statement.setInt(4, newTourLog.getDistance());
            statement.setString(5, newTourLog.getComment());
            statement.setFloat(6, newTourLog.getRating());
            statement.setInt(7, tourid);
            statement.execute();
            logger.info("Updated TourLog " + oldTourLog.getId() + " successfully");
        } catch (SQLException e) {
            logger.error("TourLog could not be updated");
            e.printStackTrace();
        }


    }

    @Override
    public void delete(String name) {
    }

    @Override

    public void deletebyid(int id) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                DELETE FROM tourlogs
                WHERE tour_id= ?;
                """)

        ) {
            statement.setInt(1, id);
            statement.execute();
            logger.info("TourLog deleted successfully");
        } catch (SQLException throwables) {
            logger.error("TourLog could not be deleted");
            throwables.printStackTrace();
        }
    }

    @Override
    public int getLogId(TourLog tourLog, String tourname) {
        int id = -1;
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tour_id
                FROM tourlogs WHERE tourname =? AND date=? AND  duration =? AND distance =? AND comment =? AND rating = ?
                """)) {
            statement.setString(1, tourname);
            statement.setString(2, tourLog.getDate());
            statement.setString(3, tourLog.getDuration());
            statement.setInt(4, tourLog.getDistance());
            statement.setString(5, tourLog.getComment());
            statement.setInt(6, tourLog.getRating());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public TourLog getLogById(int id) {
        TourLog tourLog = new TourLog();

        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                 SELECT tourname, date, duration, distance, comment, rating From tourlogs 
                WHERE tour_id=?       """)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                tourLog = new TourLog(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tourLog;
    }


}
