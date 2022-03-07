package org.easytours.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
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