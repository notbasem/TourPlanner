package com.example.tourplanner.business.API;
import com.example.tourplanner.TourPlannerApplication;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(ApiConnection.class.getSimpleName());
    private String key;
    private String time;
    private Float distance;
    private Map map;

    public ApiConnection(String from, String to) {
        logger.info("Open new API-connection");
        this.key = "MDApaG1PWkkep6VbZXPXdc8SWUYy1FFf";
        sendAsync(from, to);
    }

    public void sendAsync(String from, String to ) {
        logger.info("Sending async Request");
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
        logger.info("Parsing HTTP request");
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
        return new Map(this.key, session,boundingBox);
    }
}
