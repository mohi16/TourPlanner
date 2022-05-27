package org.easytours.tourplanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easytours.tourplanner.config.ConfigLoader;
import org.easytours.tpmodel.Tour;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import static java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

public class SimpleHttpService implements HttpService {
    private HttpHandler httpHandler;

    public SimpleHttpService(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    @Override
    public void addTour(Tour tour) throws Exception {
        HttpResponse<String> response = httpHandler.sendRequest("/add/", HttpMethod.POST, tour);
        if (!HttpStatusCode.isSame(HttpStatusCode.CREATED, response.statusCode())) {
            throw new Exception("something went wrong but i dont know what");
        }

        /*HttpRequest request = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            request = getRequest("POST", HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(tour)));
        } catch (URISyntaxException e) {
            System.out.println("invalid uri");
            throw new Exception("Invalid URL: " + ConfigLoader.getConfig().getApi());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //return false;
        }

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (ConnectException e) {
            throw new Exception("Cannot connect to: " + ConfigLoader.getConfig().getApi());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/


        //return true;


    }

    @Override
    public void deleteTour(String name) throws Exception {
        HttpResponse<String> response = httpHandler.sendRequest("/delete/" + name  + "/", HttpMethod.DELETE);
        if (!HttpStatusCode.isSame(HttpStatusCode.OK, response.statusCode())) {
            throw new Exception("something went wrong but i dont know what");
        }
        /*HttpRequest request;
        try {
            request = getRequest("PUT", HttpRequest.BodyPublishers.ofString(name));
        } catch (URISyntaxException e) {
            System.out.println("invalid uri");
            throw new Exception("Invalid URL: " + ConfigLoader.getConfig().getApi());
        }

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (ConnectException e) {
            throw new Exception("Cannot connect to: " + ConfigLoader.getConfig().getApi());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        //return true;
    }

    @Override
    public void editTour(String name, Tour newTour) throws Exception {
        HttpResponse<String> response = httpHandler.sendRequest("/edit/" + name  + "/", HttpMethod.PUT, newTour);
        if (!HttpStatusCode.isSame(HttpStatusCode.OK, response.statusCode())) {
            throw new Exception("something went wrong but i dont know what");
        }
    }

    @Override
    public Tour getTour(String name) throws Exception {
        HttpResponse<String> response = httpHandler.sendRequest("/tours/" + name  + "/", HttpMethod.GET);
        if (!HttpStatusCode.isSame(HttpStatusCode.OK, response.statusCode())) {
            throw new Exception("something went wrong but i dont know what");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), Tour.class);
    }
}