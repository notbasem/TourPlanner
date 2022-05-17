package com.example.tourplanner.business.API;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiConnection {
    String key = "Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV";
    public void sendAsync(String from, String to ) {
        URI url = URI.create("http://www.mapquestapi.com/directions/v2/route?key="+key+"&from="+from+"&to="+to.replaceAll(" ", "%20"));
        //"http://www.mapquestapi.com/directions/v2/route?key=Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV&from=Clarendon Blvd,Arlington,VA&to=2400+S+Glebe+Rd,+Arlington,+VA
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        future
                .thenApply(HttpResponse::body)
                .thenAccept(response -> System.out.println(new JSONObject(response).getJSONObject("route").toString(4)));
        future.join();



    }

    public String sendRequest(String from, String to) throws IOException, InterruptedException {
        URI url = URI.create("http://www.mapquestapi.com/directions/v2/route?key="+key+"&from="+from+"&to="+to.replaceAll(" ", "%20"));
        System.out.println(url);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        JSONObject route = json.getJSONObject("route");
        JSONObject boundingBoxObj = route.getJSONObject("boundingBox");
        JSONObject ul = boundingBoxObj.getJSONObject("ul");

        System.out.println("url: from"+from+" to "+to);
        String pretty = json.getJSONObject("route").toString(4);

        //System.out.println(pretty);

        String session = route.getString("sessionId");
        Float lng = ul.getFloat("lng");
        Float lat = ul.getFloat("lat");
        String boundingBox = lng.toString()+", "+lat.toString();

        //System.out.println("SESSION: "+session);
        //System.out.println("BOUNDING BOX: "+boundingBox);

        String link = "https://www.mapquestapi.com/staticmap/v5/map?key=Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV&size=640,680&defaultMarker=none&zoom=11&rand=15108412&session="+session+"&boudingBox="+boundingBox;

        Map map = new Map(session,boundingBox);
        return getMap(map);
    }

    public String getMap(Map map){
        String req = "https://www.mapquestapi.com/staticmap/v5/map?key=Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV&session="+map.getSession()+"&boudingBox="+map.getBoundingBox();

        return req;
    }
}
