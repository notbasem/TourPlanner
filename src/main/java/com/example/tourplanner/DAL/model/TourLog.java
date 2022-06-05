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
    private String distance;
    private String comment;
    private int rating;
    private int difficulty;

    public TourLog(String tourname, String duration, String distance,String comment, int rating, int difficulty) {
        this.tourname = tourname;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
    }

  public TourLog(String tourname,String date, String duration, String distance,String comment, int rating, int difficulty) {
        this.tourname = tourname;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
    }

    public TourLog(int id,String tourname, String duration, String distance, String comment, int rating, int difficulty) {
        this.id = id;
        this.tourname = tourname;
      //  this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
    }
   public TourLog(int id,String tourname, String date, String duration, String distance, String comment, int rating, int difficulty) {
        this.id = id;
        this.tourname = tourname;
         this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
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
                ", rating=" + rating + '\'' +
                ", difficulty=" + difficulty+
                '}';
    }

    public String toSearchString() {
        return "TourLog{" +
                "date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", distance=" + distance +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
