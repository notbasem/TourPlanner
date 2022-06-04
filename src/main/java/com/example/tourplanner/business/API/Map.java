package com.example.tourplanner.business.API;

import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Map {
    private String session;
    private String boundingBox;
    private String key;

    public Map(String key,String session, String boundingBox) {
        this.key = key;
        this.session = session;
        this.boundingBox = boundingBox;

    }

    public String getMapString(){
        return ("https://www.mapquestapi.com/staticmap/v5/map?key="+this.key+"&session="+this.getSession()+"&boudingBox="+this.getBoundingBox()).replaceAll(" ", "%20");
    }
}
