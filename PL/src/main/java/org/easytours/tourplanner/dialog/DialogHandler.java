package org.easytours.tourplanner.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.easytours.tourplanner.config.Config;
import org.easytours.tourplanner.controller.FXMLDependencyInjection;
import org.easytours.tourplanner.logging.LogManager;

import java.io.IOException;

public class DialogHandler {
    public static <T> Dialog<T> getDialog(String resourceLocation) {
        Dialog<T> dialog = new Dialog<>();
        try {
            dialog.setDialogPane(FXMLDependencyInjection.load(resourceLocation, Config.getConfig().getLang()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return dialog;
    }

    public static void showAlert(String message) {
        Dialog<ButtonType> d = new Dialog<>();
        d.setContentText(message);
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        LogManager.getLogger().warn("Showing Alert");
        d.showAndWait();
    }

    public static void showAlert(String title, String message) {
        Dialog<ButtonType> d = new Dialog<>();
        d.setTitle(title);
        d.setContentText(message);
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        d.showAndWait();
    }
}
