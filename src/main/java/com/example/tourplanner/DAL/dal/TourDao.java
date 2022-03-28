package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.DbConnection;
import com.example.tourplanner.DAL.model.Tour;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDao implements Dao <Tour>{

    @Override
    public Optional<Tour> get(String name) {
        Tour tour = null;
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tourname, description, from, to, transporttype, distance, estimatedtime, routeinformation
                FROM tours WHERE tourname =?
                """)
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(tour);
    }

    public List<Tour> getAll(){
        List<Tour> tours=new ArrayList<>();
        try ( PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT tourname, description, from, to, transporttype, distance, estimatedtime, routeinformation
                FROM tours
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
                tourname, description, from, to, transporttype, distance, estimatedtime, routeinformation)
                VALUES (?,?,?, ?, ?, ?,?, ?);
                """)
        ) {
            set(tour, statement);
            statement.execute();

            //return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
         //   return false;
        }
    }

    public void delete(Tour tour){
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                DELETE FROM tours
                WHERE tourname= ?;
                """)

        ) {
            statement.setString(1, tour.getName());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Tour tour, String newname){
        try ( PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE tours
                SET tourname=?, description=?, from=?, to=?, transporttype=?, distance=?, estimatedtime=?, routeinformation=?
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

    private void setter(Tour tour, PreparedStatement statement) throws SQLException {
        statement.setString(2, tour.getTourDescription());
        statement.setString(3, tour.getTo());
        statement.setString(4, tour.getFrom());
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


