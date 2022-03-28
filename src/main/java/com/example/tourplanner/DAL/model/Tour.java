package com.example.tourplanner.DAL.model;
import lombok.*;

@Getter
@Setter
public class Tour {
    String name;
    String tourDescription;
    String from;
    String to;
    String transportType;
    Float TourDistance;
    Integer estimatedTime;
    String routeInformation;

    public Tour (String name, String tourDescription, String from, String to, String transportType, Float TourDistance, Integer estimatedTime, String routeInformation){
        this.name = name;
        this.tourDescription = tourDescription;
        this.from = from;
        this.to = to;
        this. transportType = transportType;
        this.TourDistance = TourDistance;
        this.estimatedTime = estimatedTime;
        this.routeInformation = routeInformation;
    }

    public Tour (){
        this.name = "name";
        this.tourDescription = "tourDescription";
        this.from = "from";
        this.to = "to";
        this. transportType = "transportType";
        this.TourDistance = 4.55f;
        this.estimatedTime = 4;
        this.routeInformation = "routeInformation";
    }

}
