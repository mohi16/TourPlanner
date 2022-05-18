package org.easytours.tourplanner.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.controller.AddTourController;
import org.easytours.tourplanner.controller.ControllerFactory;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;
import org.easytours.tourplanner.model.Tour;

import java.io.IOException;

public class AddTourDialogHandler {
    public Tour createTour() {
        Dialog<ButtonType> dialog = new Dialog<>();
        try {
            dialog.setDialogPane(FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        dialog.showAndWait();

        AddTourController controller = (AddTourController) ControllerFactory.getInstance().create(AddTourController.class);

        return controller.getTour();
    }
}
