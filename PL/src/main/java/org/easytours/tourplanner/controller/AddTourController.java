package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.easytours.tourplanner.model.Tour;
import org.easytours.tourplanner.viewmodel.AddTourViewModel;

import java.time.format.DateTimeParseException;


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

    public void initialize() {
        name.textProperty().bindBidirectional(addTourViewModel.getNameProperty());
        description.textProperty().bindBidirectional(addTourViewModel.getDescriptionProperty());
        from.textProperty().bindBidirectional(addTourViewModel.getFromProperty());
        to.textProperty().bindBidirectional(addTourViewModel.getToProperty());
        transportType.textProperty().bindBidirectional(addTourViewModel.getTransportTypeProperty());
        distance.textProperty().bindBidirectional(addTourViewModel.getDistanceProperty());
        estTime.textProperty().bindBidirectional(addTourViewModel.getEstTimeProperty());
        routeInfo.textProperty().bindBidirectional(addTourViewModel.getRouteInfoProperty());

    }

    public Tour getTour(){
        try {
            return new Tour(
                    addTourViewModel.getName(),
                    addTourViewModel.getDescription(),
                    addTourViewModel.getFrom(),
                    addTourViewModel.getTo(),
                    addTourViewModel.getDistance(),
                    addTourViewModel.getEstTime(),
                    addTourViewModel.getTransportType(),
                    addTourViewModel.getRouteInfo()
            );
        }

        catch (NumberFormatException e){
             System.out.println("Distance: NaN");
        }
        catch (DateTimeParseException e){
            System.out.println("Time not a Time");
        }
        return null;
    }

    public String getName() {
        return addTourViewModel.getName();
    }



}
