package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.easytours.tourplanner.utils.TimeUtils;
import org.easytours.tourplanner.utils.Triple;
import org.easytours.tourplanner.viewmodel.AddTourViewModel;
import org.easytours.tpmodel.Tour;

import java.sql.Time;
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

    /*
    @FXML
    private TextField distance;

    @FXML
    private TextField estTimeHours;

    @FXML
    private TextField estTimeMins;

    @FXML
    private TextField estTimeSecs;
    */

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
        //distance.textProperty().bindBidirectional(addTourViewModel.getDistanceProperty());
        //estTimeHours.textProperty().bindBidirectional(addTourViewModel.getEstTimeHoursProperty());
        //estTimeMins.textProperty().bindBidirectional(addTourViewModel.getEstTimeMinsProperty());
        //estTimeSecs.textProperty().bindBidirectional(addTourViewModel.getEstTimeSecsProperty());
        routeInfo.textProperty().bindBidirectional(addTourViewModel.getRouteInfoProperty());

    }

    public Tour getTour(){
        try {
            System.out.println("Name " + addTourViewModel.getName());
            //System.out.println("distance: " + addTourViewModel.getDistance());
            //System.out.println("time: " + addTourViewModel.getEstTimeHours());
            return new Tour(
                    addTourViewModel.getName(),
                    addTourViewModel.getDescription(),
                    addTourViewModel.getFrom(),
                    addTourViewModel.getTo(),
                    0,
                    0,  // TimeUtils.constructTime(addTourViewModel.getEstTimeHours(), addTourViewModel.getEstTimeMins(), addTourViewModel.getEstTimeSecs()),
                    addTourViewModel.getTransportType(),
                    addTourViewModel.getRouteInfo()

            );


        }

        catch (NumberFormatException e){
            System.out.println("NaN");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Time not a time");
        }
        catch (NullPointerException e) {
            System.out.println("Null value");
        }
        return null;
    }

    public String getName() {
        return addTourViewModel.getName();
    }


    public void clear() {
        addTourViewModel.setName("");
        addTourViewModel.setDescription("");
        addTourViewModel.setFrom("");
        addTourViewModel.setTo("");
        //addTourViewModel.setDistance("");
        //addTourViewModel.setEstTimeHours("");
        //addTourViewModel.setEstTimeMins("");
        //addTourViewModel.setEstTimeSecs("");
        addTourViewModel.setTransportType("");
        addTourViewModel.setRouteInfo("");
    }

    public void fill(Tour tour) {
        addTourViewModel.setName(tour.getName());
        addTourViewModel.setDescription(tour.getDescription());
        addTourViewModel.setFrom(tour.getFrom());
        addTourViewModel.setTo(tour.getTo());
        //addTourViewModel.setDistance(String.valueOf(tour.getDistance()));
/*        Triple<Integer, Integer, Integer> time = TimeUtils.deconstructTime(tour.getEstTime());
        addTourViewModel.setEstTimeHours(String.valueOf(time.getValue1()));
        addTourViewModel.setEstTimeMins(String.valueOf(time.getValue2()));
        addTourViewModel.setEstTimeSecs(String.valueOf(time.getValue3()));*/
        addTourViewModel.setTransportType(tour.getTransportType());
        addTourViewModel.setRouteInfo(tour.getRouteInfo());
    }
}
