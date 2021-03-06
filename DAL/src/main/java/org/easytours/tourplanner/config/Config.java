package org.easytours.tourplanner.config;

import org.easytours.tpmodel.config.ConfigLoader;

import java.awt.desktop.AppForegroundEvent;
import java.io.IOException;

public class Config {
    private static AppConfig appConfig;

    public static void load() throws IOException {
        appConfig = ConfigLoader.load(AppConfig.class, "./appconfig.yaml");
    }

    public static AppConfig getConfig(){
        return appConfig;
    }

}
