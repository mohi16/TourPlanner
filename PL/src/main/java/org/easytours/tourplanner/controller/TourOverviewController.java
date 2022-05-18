package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;

import org.easytours.tourplanner.model.Tour;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;

import java.io.IOException;

public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;
    private final AddTourDialogHandler dialogHandler;

    @FXML
    private Button addTourButton;

    @FXML
    private Button deleteTourButton;

    @FXML
    private ListView<String> toursList;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel, AddTourDialogHandler dialogHandler) {
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.dialogHandler = dialogHandler;
    }

    public String addTour() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(FXMLDependencyInjection.load("addtour.fxml", AppConfig.getLocale()));

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
            tourOverviewViewModel.toursListProperty().add(tour.getName());
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

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");
    }
}
