package com.example.tourplanner.DAL.dal;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {
   Optional<T> get(String name);

    List<T> getAll();
    void create(T t);

   // void update(T t, List <?> params);
    void update(T t, String name);

    void delete (T t);
}
