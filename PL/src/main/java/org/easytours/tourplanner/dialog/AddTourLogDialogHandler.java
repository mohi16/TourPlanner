package org.easytours.tourplanner.dialog;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.easytours.tourplanner.controller.AddTourController;
import org.easytours.tourplanner.controller.AddTourLogController;
import org.easytours.tourplanner.controller.ControllerFactory;
import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;

import java.util.Optional;

public class AddTourLogDialogHandler {
    private Dialog<ButtonType> getDialog() {
        return DialogHandler.getDialog("addtourlog.fxml");
    }

    private AddTourLogController getController() {
        return (AddTourLogController) ControllerFactory.getInstance().create(AddTourLogController.class);
    }

    public TourLog createTourLog() {
        Dialog<ButtonType> dialog = getDialog();
        if (null == dialog) {
            return null;
        }
        AddTourLogController controller = getController();
        if (null == controller) {
            return null;
        }

        controller.clear();

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && ButtonBar.ButtonData.OK_DONE == result.get().getButtonData()) {
            return controller.getTourLog();
        } else {
            return null;
        }
    }

    public TourLog editTourLog(TourLog tourLog) {
        Dialog<ButtonType> dialog = getDialog();
        if (null == dialog) {
            return null;
        }
        AddTourLogController controller = getController();
        if (null == controller) {
            return null;
        }

        controller.fill(tourLog);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && ButtonBar.ButtonData.OK_DONE == result.get().getButtonData()) {
            return controller.getTourLog();
        } else {
            return null;
        }
    }
}
