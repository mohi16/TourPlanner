package org.easytours.tourplanner.dialog;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.easytours.tourplanner.controller.AddTourController;
import org.easytours.tourplanner.controller.ControllerFactory;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;
import org.easytours.tpmodel.Tour;

import java.io.IOException;
import java.util.Optional;

public class AddTourDialogHandler {
    private Dialog<ButtonType> getDialog() {
        /*Dialog<ButtonType> dialog = new Dialog<>();
        try {
            dialog.setDialogPane(FXMLDependencyInjection.load("addtour.fxml", ConfigLoader.getConfig().getLang()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return dialog;*/

        return DialogHandler.getDialog("addtour.fxml");
    }

    private AddTourController getController() {
        return (AddTourController) ControllerFactory.getInstance().create(AddTourController.class);
    }

    public Tour createTour() {
        Dialog<ButtonType> dialog = getDialog();
        if (null == dialog) {
            return null;
        }
        AddTourController controller = getController();
        if (null == controller) {
            return null;
        }

        controller.clear();

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && ButtonData.OK_DONE == result.get().getButtonData()) {
            return controller.getTour();
        } else {
            return null;
        }
    }

    public Tour editTour(Tour tour) {
        Dialog<ButtonType> dialog = getDialog();
        if (null == dialog) {
            return null;
        }
        AddTourController controller = getController();
        if (null == controller) {
            return null;
        }

        controller.fill(tour);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && ButtonData.OK_DONE == result.get().getButtonData()) {
            return controller.getTour();
        } else {
            return null;
        }
    }
}
