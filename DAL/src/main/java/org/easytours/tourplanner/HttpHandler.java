package org.easytours.tourplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.easytours.tourplanner.config.Config;


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

    public <B> HttpResponse<String> sendRequest(String route, HttpMethod method, B body) throws Exception {
        HttpClient client = getClient();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request  = getRequest(route, method.name(), HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)));

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpClient getClient() {
        return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    }

    public HttpRequest getRequest(String route, String method, HttpRequest.BodyPublisher body) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(Config.getConfig().getApi() + route))
            .method(method, body)
            .header("Content-Type", "application/json")
            .build();
    }
}
