package org.easytours.tourplanner.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.config.Config;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;

import org.easytours.tourplanner.dialog.DialogHandler;
import org.easytours.tourplanner.utils.Wrapper;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.easytours.tpmodel.Tour;

import java.io.IOException;

public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;
    private final AddTourDialogHandler dialogHandler;

    @FXML
    private Button addTourButton;

    @FXML
    private Button deleteTourButton;

    @FXML
    private Button editTourButton;

    @FXML
    private ListView<String> toursList;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel, AddTourDialogHandler dialogHandler) {
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.dialogHandler = dialogHandler;

        /*tourOverviewViewModel.toursListProperty().add("Tour 1");
        tourOverviewViewModel.toursListProperty().add("Tour 2");
        tourOverviewViewModel.toursListProperty().add("Tour 3");*/
    }

    @FXML
    public void onAddTourButtonClick() {
        System.out.println("Add");

        Tour tour = dialogHandler.createTour();
        if (null != tour) {
            try {
                Wrapper<Boolean> badTour = new Wrapper<>(false);
                Thread th = new Thread(() -> {
                    try {
                        App.getBusinessLogic().addTour(tour);
                        tourOverviewViewModel.toursListProperty().add(tour.getName());
                    } catch (IllegalArgumentException e) {
                        badTour.set(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                th.start();
                Dialog<String> d = new Dialog<>();
                d.setContentText(App.getResourceBundle().getString("Msg_Wait"));
                d.show();
                th.join();
                // close waiting dialog
                d.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
                d.close();
                if (badTour.get()) {
                    DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");

        try {
            String tourname = null;
            try {
                tourname = getSelectedTourName();
            } catch (IndexOutOfBoundsException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
                return;
            }

            App.getBusinessLogic().deleteTour(tourname);
            tourOverviewViewModel.toursListProperty().remove(getSelectedTourIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSelectedTourIndex() {
        return toursList.getSelectionModel().getSelectedIndex();
    }

    private String getSelectedTourName() {
        return tourOverviewViewModel.toursListProperty().get(getSelectedTourIndex());
    }

    @FXML
    public void onEditTourButtonClick() {
        System.out.println("Edit");

        String name = null;
        try {
            name = getSelectedTourName();
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
            return;
        }
        int idx = getSelectedTourIndex();
        Tour tour = null;
        try {
            tour = dialogHandler.editTour(App.getBusinessLogic().getTour(name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != tour) {
            try {
                App.getBusinessLogic().editTour(name, tour);
                tourOverviewViewModel.toursListProperty().set(idx, tour.getName());
            } catch (IllegalArgumentException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        toursList.setItems(tourOverviewViewModel.toursListProperty());
    }
}
