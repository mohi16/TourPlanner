package org.easytours.tourplanner.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {
    // load given file ("path.fxml")
    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    // get loader and resource bundles
    public static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
            FXMLDependencyInjection.class.getResource("/org/easytours/tourplanner/view/" + location),
            ResourceBundle.getBundle("org.easytours.tourplanner.view." + "gui_strings", locale),
            new JavaFXBuilderFactory(),
            (controllerClass) -> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}

