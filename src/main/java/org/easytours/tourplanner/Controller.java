package org.easytours.tourplanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class Controller {

    @FXML
    private TextField searchTextField;

    private final ViewModel viewModel = new ViewModel();

    @FXML
    protected void onSearchButtonClick(){
        System.out.println(viewModel.getSearchString());
        searchTextField.clear();
    }

    @FXML
    protected void onHelloButtonClick(){
    }

    public void initialize() {
        searchTextField.textProperty().bindBidirectional(viewModel.searchStringProperty());


    }



}