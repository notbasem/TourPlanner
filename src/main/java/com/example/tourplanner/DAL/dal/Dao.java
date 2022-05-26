package com.example.tourplanner.DAL.dal;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {
   Optional<T> get(String name);

    List<T> getAll();
    void create(T t);

    void updateTour(T oldT, T newT);

    void delete (String name);
}
