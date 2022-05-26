package org.easytours.tourplanner.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

public class AppConfig {
    private String lang; // de, en, it
    private String api; // URL of REST Server yes yes

    public AppConfig(String lang, String api) {
        this.lang = lang;
        this.api = api;
    }

    public AppConfig() {}

    public Locale getLang() {
        if ("de".equals(lang)) {
            return Locale.GERMAN;
        } else if ("en".equals(lang)) {
            return Locale.ENGLISH;
        } else if ("it".equals(lang)) {
            return Locale.ITALIAN;
        } else {
            throw new RuntimeException("unknown language");
        }
    }

    public String getApi() {
        return api;
    }
}
