package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.model.TourLog;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {
   //ObservableList<TourLog> getlogs(String name);
 Optional<T> get(String name);

    List<T> getAll();

    void create(T t);

    void update(T oldT, T newT);


 void delete (String name);


    void deletebyid(int id);

    int getid(TourLog tourLog, String tourname);

 ObservableList<T> getlogs(String selectedtournamne);

    TourLog getLogById(int id);
}
