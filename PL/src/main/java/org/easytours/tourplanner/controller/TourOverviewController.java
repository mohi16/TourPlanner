package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.dialog.TourOverviewDialogHandler;
import org.easytours.tourplanner.viewmodel.AddTourViewModel;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;

import java.io.IOException;
import java.util.Locale;

public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;

    @FXML
    private Button addTourButton;

    @FXML
    private Button deleteTourButton;

    @FXML
    private ListView<String> toursList;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
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

        try {
            String name = addTour();
            if (!name.isEmpty()) {
                tourOverviewViewModel.toursListProperty().add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
