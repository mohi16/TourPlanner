package org.easytours.tourplanner;

import org.easytours.tourplanner.config.ConfigLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpHandler {
    public <T> HttpResponse<T> sendRequest(String route, HttpMethod method) {
        return null;
    }

    public <T> HttpResponse<T> sendRequest(String route, HttpMethod method, String body) {
        return null;
    }

    public <T, B> HttpResponse<T> sendRequest(String route, HttpMethod method, B body) {
        return null;
    }

    private HttpClient getClient() {
        return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    }

    public HttpRequest getRequest(String method, HttpRequest.BodyPublisher body) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(ConfigLoader.getConfig().getApi()))
            .method(method, body)
            .header("Content-Type", "application/json")
            .build();
    }
}
