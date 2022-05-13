package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
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
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale()));
        dialog.showAndWait();
        //Parent root = FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale());

        /*
        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 700);
        stage.setTitle("Add Tour");
        stage.setScene(scene);
        //stage.sizeToScene();
        //stage.show();
        stage.initModality(Modality.APPLICATION_MODAL);
         */
        //return stage;
        return null;

    }

    @FXML
    public void onAddTourButtonClick() {
        System.out.println("Add");

        /*
        Dialog<String> dialog = new Dialog<>();
        try {
            dialog.getDialogPane().setContent(FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale()));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            return;
        }
        dialog.showAndWait();
         */

        //////////////////////////////////////////////
        Dialog<ButtonType> dialog = new Dialog<>();
        try {
            dialog.setDialogPane((DialogPane)FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale()));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            return;
        }
        dialog.showAndWait();

        System.out.println(dialog.getResult().getButtonData().getTypeCode());
        /////////////////////////////////////////////

        /*
        Stage dialog = null;
        try {
            dialog = createAddDialog();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        dialog.showAndWait();
        */
    }

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");
    }
}
