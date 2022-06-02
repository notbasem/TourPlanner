package com.example.tourplanner.DAL.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourLog {
    private int id;
    private String tourname;
    private String date;
    private String duration;
    private int distance;
    private String comment;
    private int rating;

    public TourLog(String tourname, String date, String duration, int distance, String comment, int rating) {
        this.tourname = tourname;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
    }

    public TourLog(int id,String tourname, String date, String duration, int distance, String comment, int rating) {
        this.id = id;
        this.tourname = tourname;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
    }

    public TourLog() {

    }
   @Override
    public String toString() {
        return "TourLog{" +
                "id=" + id +
                ", tourname='" + tourname + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", distance=" + distance +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
