package org.easytours.tourplanner;

import org.easytours.tpmodel.http.HttpStatusCode;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class MockHttpResponse<T> implements HttpResponse<T> {
    private int statusCode;
    private T body;

    public MockHttpResponse(HttpStatusCode statusCode, T body) {
        this.statusCode = statusCode.getCode();
        this.body = body;
    }

    public MockHttpResponse(HttpStatusCode statusCode) {
        this(statusCode, null);
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public HttpRequest request() {
        return null;
    }

    @Override
    public Optional<HttpResponse<T>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public T body() {
        return body;
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
