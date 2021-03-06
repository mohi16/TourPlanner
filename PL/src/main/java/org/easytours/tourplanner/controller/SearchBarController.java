package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.easytours.tourplanner.logging.LogManager;
import org.easytours.tourplanner.viewmodel.SearchBarViewModel;

public class SearchBarController {
    @FXML
    private TextField searchTextField;

    private final SearchBarViewModel searchBarViewModel;

    public SearchBarController(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    @FXML
    protected void onSearchButtonClick() {
        LogManager.getLogger().info("Search String: " + searchBarViewModel.getSearchString());
        TourOverviewController toc = (TourOverviewController) ControllerFactory.getInstance().create(TourOverviewController.class);
        toc.loadTours(searchBarViewModel.getSearchString());
        //searchTextField.clear();
    }

    @FXML
    public void initialize() {
        searchTextField.textProperty().bindBidirectional(searchBarViewModel.searchStringProperty());
    }

}
