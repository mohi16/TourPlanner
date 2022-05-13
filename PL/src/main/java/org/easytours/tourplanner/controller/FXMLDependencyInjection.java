package org.easytours.tourplanner.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {
    // load given file ("path.fxml")
    /*public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }*/

    /**
     * Load a given FXML file with a given locale.
     *
     * @param location The location of the FXML file.
     * @param locale The locale to use.
     * @return The content of the FXML file as an object of type {@code T}.
     * @param <T> Type of the loaded root, e.g. Parent, DialogPane.
     * @throws IOException same as FXMLLoader#load(...).
     */
    public static <T> T load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    // get loader and resource bundles
    private static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
            FXMLDependencyInjection.class.getResource("/org/easytours/tourplanner/view/" + location),
            ResourceBundle.getBundle("org.easytours.tourplanner.view." + "gui_strings", locale),
            new JavaFXBuilderFactory(),
            (controllerClass) -> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}
