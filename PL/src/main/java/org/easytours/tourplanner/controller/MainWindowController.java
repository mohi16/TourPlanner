package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import org.easytours.tourplanner.viewmodel.MainWindowViewModel;

public class MainWindowController {
    @FXML
    private SearchBarController searchBarController;

    @FXML
    private TourOverviewController tourOverviewController;

    @FXML
    private TourDetailsController tourDetailsController;

    private final MainWindowViewModel mainWindowViewModel;

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
}