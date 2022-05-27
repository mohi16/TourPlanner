package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.config.ConfigLoader;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;

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

    public String addTour() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(FXMLDependencyInjection.load("addtour.fxml", ConfigLoader.getConfig().getLang()));

        dialog.showAndWait();

        //FXMLLoader loader = FXMLDependencyInjection.getLoader("addtour.fxml", AppConfig.getLocale());
        //AddTourController controller = loader.getController();
        AddTourController controller = (AddTourController)ControllerFactory.getInstance().create(AddTourController.class);

        return controller.getName();
    }

    @FXML
    public void onAddTourButtonClick() {
        System.out.println("Add");

        Tour tour = dialogHandler.createTour();
        if (null != tour) {
            /*Thread th = new Thread(() -> {
                Dialog<String> d = new Dialog<>();
                d.showAndWait();
            });*/
            try {
                /*if (App.getBusinessLogic().addTour(tour)) {
                    tourOverviewViewModel.toursListProperty().add(tour.getName());
                }*/
                // start waiting dialog

                //th.start();
                App.getBusinessLogic().addTour(tour);
                // close waiting dialog
                //th.join();
                tourOverviewViewModel.toursListProperty().add(tour.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");

        try {
            /*if (App.getBusinessLogic().deleteTour(getSelectedTourName())) {
                tourOverviewViewModel.toursListProperty().remove(getSelectedTourIndex());
            }*/
            App.getBusinessLogic().deleteTour(getSelectedTourName());
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

        String name = getSelectedTourName();
        int idx = getSelectedTourIndex();
        Tour tour = null;
        try {
            tour = dialogHandler.editTour(App.getBusinessLogic().getTour(name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != tour) {
            try {
                /*if (App.getBusinessLogic().editTour(name, tour)) {
                    tourOverviewViewModel.toursListProperty().set(idx, tour.getName());
                }*/
                App.getBusinessLogic().editTour(name, tour);
                tourOverviewViewModel.toursListProperty().set(idx, tour.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    public int getTourCount(){
        return tourOverviewViewModel.getTourCount();
    }

     */

    @FXML
    public void initialize() {
        //searchTextField.textProperty().bindBidirectional(searchBarViewModel.searchStringProperty());
        toursList.setItems(tourOverviewViewModel.toursListProperty());
    }
}
