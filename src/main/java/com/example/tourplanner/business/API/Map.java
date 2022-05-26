package com.example.tourplanner.business.API;

import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Map {
    private String size;
    private String defaultMarker ;
    private String zoom = "11";
    private String rand = "15108412";
    private String session;
    private String boundingBox;

    public Map(String session, String boundingBox) {
        this.session = session;
        this.boundingBox = boundingBox;
        this.size = "640,680";
        this.defaultMarker = "none";
        this.zoom = "11";
        this.rand = "15108412";
    }

    public String getMapString(){
        return ("https://www.mapquestapi.com/staticmap/v5/map?key=Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV&session="+this.getSession()+"&boudingBox="+this.getBoundingBox()).replaceAll(" ", "%20");
    }
}
