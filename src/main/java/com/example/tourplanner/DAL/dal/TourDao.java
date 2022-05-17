package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.DbConnection;
import com.example.tourplanner.DAL.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TourDao implements Dao <Tour>{

    @Override
    public Optional<Tour> get(String name) {
        Tour tour = null;
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tourname, description, fromdistance, todistance, transporttype, distance, estimatedtime, routeinformation
                FROM tours WHERE tourname =?
                """)
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if( resultSet.next() ) {

                tour = new Tour(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getFloat(6),
                        resultSet.getInt(7),
                        resultSet.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(tour);
    }

    public ObservableList<Tour> getAll(){
        ObservableList<Tour> tours = FXCollections.observableArrayList();;
        try ( PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tourname, description, fromdistance, todistance, transporttype, distance, estimatedtime, routeinformation
                FROM tours ORDER BY tourid
                """)
        ){
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                tours.add(new Tour(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getFloat(6),
                                resultSet.getInt(7),
                                resultSet.getString(8)
                        )
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();}

        return tours;
    }


    public void create(Tour tour) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                INSERT INTO tours
                (tourname, description, fromdistance, todistance, transporttype, distance, estimatedtime, routeinformation)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """)
        ) {
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getTourDescription());
            statement.setString(3, tour.getTo());
            statement.setString(4, tour.getFrom());
            statement.setString(5, tour.getTransportType());
            statement.setFloat(6, tour.getTourDistance());
            statement.setInt(7, tour.getEstimatedTime());
            statement.setString(8, tour.getRouteInformation());
            statement.execute();

            //return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //   return false;
        }
    }

    public void delete(String tourname){
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                DELETE FROM tours
                WHERE tourname= ?;
                """)

        ) {
            statement.setString(1, tourname);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Tour tour, String newname){
        try ( PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE tours
                SET tourname=?, description=?, fromdistance=?, todistance=?, transporttype=?, distance=?, estimatedtime=?, routeinformation=?
                WHERE tourname=? ;
                """)
        ) {
            statement.setString(1, newname);
            setter(tour, statement);
            statement.setString(9, tour.getName());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTour(Tour oldT, Tour newT){
        try ( PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE tours
                SET tourname=?, description=?, fromdistance=?, todistance=?, transporttype=?, distance=?, estimatedtime=?, routeinformation=?
                WHERE tourname=? ;
                """)
        ) {
            statement.setString(1, newT.getName());
            statement.setString(2, newT.getTourDescription());
            statement.setString(3, newT.getFrom());
            statement.setString(4, newT.getTo());
            statement.setString(5, newT.getTransportType());
            statement.setFloat(6, newT.getTourDistance());
            statement.setInt(7, newT.getEstimatedTime());
            statement.setString(8, newT.getRouteInformation());
            statement.setString(9, oldT.getName());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setter(Tour tour, PreparedStatement statement) throws SQLException {
        statement.setString(2, tour.getTourDescription());
        statement.setString(3, tour.getFrom());
        statement.setString(4, tour.getTo());
        statement.setString(5, tour.getTransportType());
        statement.setFloat(6, tour.getTourDistance());
        statement.setInt(7, tour.getEstimatedTime());
        statement.setString(8, tour.getRouteInformation());
    }

    private void set(Tour tour, PreparedStatement statement) throws SQLException {
        statement.setString(1, tour.getName());
        setter(tour, statement);
        statement.execute();
    }


}


