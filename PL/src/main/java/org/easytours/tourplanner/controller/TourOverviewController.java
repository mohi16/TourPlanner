package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;

import java.io.IOException;
import java.util.Locale;

public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;

    @FXML
    private Button addTourButton;

    @FXML
    private Button deleteTourButton;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
    }

    public Stage createAddDialog() throws IOException {
        Parent root = FXMLDependencyInjection.load("addTour.fxml", AppConfig.LOCALE);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 700);
        stage.setTitle("Add Tour");
        stage.setScene(scene);
        //stage.sizeToScene();
        //stage.show();
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;

    }

    @FXML
    public void onAddTourButtonClick() {
        System.out.println("Add");

        Stage dialog = null;
        try {
            dialog = createAddDialog();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        dialog.showAndWait();
    }

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");
    }
}
