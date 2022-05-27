package org.easytours.tourplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.easytours.tourplanner.config.ConfigLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpHandler {
    public HttpResponse<String> sendRequest(String route, HttpMethod method) throws Exception {
        HttpClient client = getClient();
        HttpRequest request  = getRequest(route, method.name(), HttpRequest.BodyPublishers.noBody());

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendRequest(String route, HttpMethod method, String body) throws Exception {
        HttpClient client = getClient();
        HttpRequest request  = getRequest(route, method.name(), HttpRequest.BodyPublishers.ofString(body));

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public <T, B> HttpResponse<T> sendRequest(String route, HttpMethod method, B body) {
        HttpClient client = getClient();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request  = getRequest(route, method.name(), HttpRequest.BodyPublishers.ofString(body));

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpClient getClient() {
        return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    }

    public HttpRequest getRequest(String route, String method, HttpRequest.BodyPublisher body) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(ConfigLoader.getConfig().getApi()))
            .method(method, body)
            .header("Content-Type", "application/json")
            .build();
    }
}
