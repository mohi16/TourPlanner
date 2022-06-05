package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.dialog.DialogHandler;
import org.easytours.tourplanner.viewmodel.MainWindowViewModel;

import java.io.File;

public class MainWindowController {
    @FXML
    private SearchBarController searchBarController;

    @FXML
    private TourOverviewController tourOverviewController;

    @FXML
    private TourDetailsController tourDetailsController;

    private final MainWindowViewModel mainWindowViewModel;

    @FXML
    private VBox mainVBox;

    public MainWindowController(MainWindowViewModel mainWindowViewModel){
        this.mainWindowViewModel = mainWindowViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainWindowViewModel;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void onHelloButtonClick(){

    }

    @FXML
    public void onSingleReportClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File savedFile = fileChooser.showSaveDialog(App.getScene().getWindow());
        if (null == savedFile) {
            return;
        }
        try {
            App.getBusinessLogic().generateSingleReport(savedFile, tourOverviewController.getSelectedTourName());
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
        } catch (Exception e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("SomethingWrong"));
        }
    }

    @FXML
    public void onAllReportClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File savedFile = fileChooser.showSaveDialog(App.getScene().getWindow());
        if (null == savedFile) {
            return;
        }
        try {
            App.getBusinessLogic().generateSummaryReport(savedFile);
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("SomethingWrong"));
        }
    }

    @FXML
    public void onImportClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Tours");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File savedFile = fileChooser.showOpenDialog(App.getScene().getWindow());
        if (null == savedFile) {
            return;
        }
        try {
            App.getBusinessLogic().importTours(savedFile);
            tourOverviewController.loadTours();
        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("SomethingWrong"));
        }
    }

    @FXML
    public void onExportClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Tours");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File savedFile = fileChooser.showSaveDialog(App.getScene().getWindow());
        if (null == savedFile) {
            return;
        }
        try {
            App.getBusinessLogic().exportTours(savedFile);
        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("SomethingWrong"));
        }
    }
}