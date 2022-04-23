package org.easytours.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;


import java.io.IOException;
import java.util.Locale;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BusinessClass bc = new BusinessClass();
        bc.irgendwas();

        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Parent root = FXMLDependencyInjection.load("view.fxml", Locale.GERMAN);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
        Button btn = new Button();
        btn.setMaxWidth(Double.MAX_VALUE);

    }

    public static void main(String[] args) {
        launch();
    }
}