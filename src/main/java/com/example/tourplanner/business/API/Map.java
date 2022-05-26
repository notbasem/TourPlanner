package com.example.tourplanner.business.API;

import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Map {
    private String session;
    private String boundingBox;

    public Map(String session, String boundingBox) {
        this.session = session;
        this.boundingBox = boundingBox;

    }

    public String getMapString(){
        return ("https://www.mapquestapi.com/staticmap/v5/map?key=Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV&session="+this.getSession()+"&boudingBox="+this.getBoundingBox()).replaceAll(" ", "%20");
    }
}
