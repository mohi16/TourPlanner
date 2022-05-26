package org.easytours.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.easytours.tourplanner.config.ConfigLoader;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;


import java.io.IOException;
import java.util.Locale;

public class App extends Application {
    private static BusinessLogic businessLogic;

    @Override
    public void start(Stage stage) throws IOException {
        BusinessClass bc = new BusinessClass();
        bc.irgendwas(); //test

        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Parent root = FXMLDependencyInjection.load("view.fxml", ConfigLoader.getConfig().getLang());
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();

        Button btn = new Button();
        btn.setMaxWidth(Double.MAX_VALUE);

    }

    public static void main(String[] args) {
        try {
            ConfigLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        setBusinessLogic(new SimpleBusinessLogic(new SimpleHttpService(new HttpHandler())));

        launch();
    }

    public static BusinessLogic getBusinessLogic() {
        return businessLogic;
    }

    public static void setBusinessLogic(BusinessLogic businessLogic) {
        App.businessLogic = businessLogic;
    }
}