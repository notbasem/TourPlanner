package com.example.tourplanner.DAL.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourLog {
    String tourname;
    String date;
    String duration;
    int distance;
    String comment;
    int rating;

    public TourLog(String tourname, String date, String duration, int distance, String comment, int rating){
        this.tourname = tourname;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.rating = rating;
    }


}
