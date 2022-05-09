package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.easytours.tourplanner.viewmodel.AddTourViewModel;

public class AddTourController {
    private final AddTourViewModel addTourViewModel;

    @FXML
    private TextField name;

    @FXML
    private TextField from;

    @FXML
    private TextArea description;

    @FXML
    private TextField to;

    @FXML
    private TextField transportType;

    @FXML
    private TextField distance;

    @FXML
    private TextField estTime;

    @FXML
    private TextField routeInfo;


    public AddTourController(AddTourViewModel addTourViewModel) {
        this.addTourViewModel = addTourViewModel;
    }
}
