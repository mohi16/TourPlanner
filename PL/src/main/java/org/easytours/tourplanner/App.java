package org.easytours.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.easytours.tourplanner.config.Config;
import org.easytours.tpmodel.config.ConfigLoader;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    private static BusinessLogic businessLogic;

    @Override
    public void start(Stage stage) throws IOException {
        BusinessClass bc = new BusinessClass();
        bc.irgendwas(); //test

        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Parent root = FXMLDependencyInjection.load("view.fxml", Config.getConfig().getLang());
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();

        Button btn = new Button();
        btn.setMaxWidth(Double.MAX_VALUE);

    }

    public static void main(String[] args) {
        try {
            Config.load();

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

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("org.easytours.tourplanner.view.gui_strings", Config.getConfig().getLang());
    }
}