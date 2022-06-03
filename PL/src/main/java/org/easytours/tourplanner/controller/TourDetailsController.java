package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.easytours.tourplanner.viewmodel.TourDetailsViewModel;
import org.easytours.tpmodel.Tour;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class TourDetailsController {
    private final TourDetailsViewModel tourDetailsViewModel;

    @FXML
    private ImageView tourImageView;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextField routeInfoTextField;

    @FXML
    private TextField estTimeTextField;

    @FXML
    private TextField transportTypeTextField;


    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public void loadTour(Tour tour) {
        setImage(Base64.getDecoder().decode(tour.getImage()));
        tourDetailsViewModel.getFrom().set(tour.getFrom());
        tourDetailsViewModel.getTo().set(tour.getTo());
        tourDetailsViewModel.getDescription().set(tour.getDescription());
        tourDetailsViewModel.setDistance(tour.getDistance());
        tourDetailsViewModel.getTransportType().set(tour.getTransportType());
        tourDetailsViewModel.setEstTime(tour.getEstTime());
        tourDetailsViewModel.getRouteInfo().set(tour.getRouteInfo());
    }


    public void setImage(byte[] image) {
        System.out.println("image set");
        InputStream inputStream = new ByteArrayInputStream(image);
        /*OutputStream writer = null;
        try {
            writer = new FileOutputStream(new File("./img.png"));
            writer.write(image);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        tourImageView.setImage(new Image(inputStream));
        //tourImageView.setImage(new Image("file:img.png"));
    }

    @FXML
    public void initialize() {
        fromTextField.textProperty().bindBidirectional(tourDetailsViewModel.getFrom());
        toTextField.textProperty().bindBidirectional(tourDetailsViewModel.getTo());
        descriptionTextField.textProperty().bindBidirectional(tourDetailsViewModel.getDescription());
        routeInfoTextField.textProperty().bindBidirectional(tourDetailsViewModel.getRouteInfo());
        estTimeTextField.textProperty().bindBidirectional(tourDetailsViewModel.getEstTime());
        transportTypeTextField.textProperty().bindBidirectional(tourDetailsViewModel.getTransportType());
        distanceTextField.textProperty().bindBidirectional(tourDetailsViewModel.getDistance());
    }
}
