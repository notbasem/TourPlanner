package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.DbConnection;
import com.example.tourplanner.DAL.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog> {

    public Optional get(String name) {
        TourLog tourLog = null;
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT  date, duration,distance,comment, rating
                FROM tours WHERE tourname =?
                """)
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                tourLog = new TourLog(
                        name,
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(tourLog);

    }

    public ObservableList<TourLog> getAll() {
        ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tourname, date, duration, distance, comment, rating From tourlogs
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tourLogs.add(new TourLog(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getInt(6)


                        )
                );
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
                """)
        ) {
            statement.setString(1, tourLog.getTourname());
            statement.setString(2, tourLog.getDate());
            statement.setString(3, tourLog.getDuration());
            statement.setInt(4, tourLog.getDistance());
            statement.setString(5, tourLog.getComment());
            statement.setInt(6, tourLog.getRating());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void update(TourLog oldTourLog, TourLog newTourLog) {
        int tourid = getid(oldTourLog);

        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE tourlogs
                SET tourname=?,  date=?,  duration=?, distance=?, comment=?, rating=?
                WHERE tourid=? ;
                """)
        ) {
            statement.setString(1, newTourLog.getTourname());
            statement.setString(2, newTourLog.getDate());
            statement.setString(3, newTourLog.getDuration());
            statement.setInt(4, newTourLog.getDistance());
            statement.setString(5, newTourLog.getComment());
            statement.setFloat(6, newTourLog.getRating());
            statement.setInt(7, tourid);

            statement.execute();
        } catch (SQLException e) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public int getid(TourLog tourLog) {
        int id = -1;
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tour_id
                FROM tourlogs WHERE tourname =? AND date=? AND  duration =? AND distance =? AND comment =? AND rating = ?
                """)
        ) {
            statement.setString(1, tourLog.getTourname());
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


}
