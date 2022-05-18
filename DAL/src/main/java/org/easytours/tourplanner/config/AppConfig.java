package org.easytours.tourplanner.config;

public class AppConfig {
    private String lang;
    private Db db;

    public AppConfig(String lang, Db db) {
        this.lang = lang;
        this.db = db;
    }

    public AppConfig() {}
}
