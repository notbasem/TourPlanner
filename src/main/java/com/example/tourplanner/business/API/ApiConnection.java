package com.example.tourplanner.business.API;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
@Getter
@Setter

public class ApiConnection {
    private String key;
    private String time;
    private Float distance;
    private Map map;

    public ApiConnection(String from, String to) {
        System.out.println("NEUE API CONNECTION");
        this.key = "Y4xILB7lh36v0IqkJc2kEAmqa6T52OoV";
        sendAsync(from, to);
    }

    public void sendAsync(String from, String to ) {
        URI url = URI.create(("http://www.mapquestapi.com/directions/v2/route?key="+key+"&from="+from+"&to="+to).replaceAll(" ", "%20"));
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        future
                .thenApply(HttpResponse::body)
                .thenAccept((response) -> {
                    //System.out.println(new JSONObject(response).getJSONObject("route").toString(4));
                    this.map = parseHttpRequest(response);
                })
                .join();
    }

    private Map parseHttpRequest(String response) {
        JSONObject json = new JSONObject(response);
        JSONObject route = json.getJSONObject("route");
        JSONObject boundingBoxObj = route.getJSONObject("boundingBox");
        JSONObject ul = boundingBoxObj.getJSONObject("ul");


        String session = route.getString("sessionId");
        Float lng = ul.getFloat("lng");
        Float lat = ul.getFloat("lat");
        String boundingBox = lng+", "+lat;

        this.distance = route.getFloat("distance");
        this.time = route.getString("formattedTime");

        System.out.println("Time: "+this.time);
        System.out.println("Distance: "+this.distance);
        return new Map(session,boundingBox);
    }
}
