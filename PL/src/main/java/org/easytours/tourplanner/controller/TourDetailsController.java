package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.easytours.tourplanner.viewmodel.TourDetailsViewModel;

import java.io.*;

public class TourDetailsController {
    private final TourDetailsViewModel tourDetailsViewModel;

    @FXML
    private ImageView tourImageView;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel){
        this.tourDetailsViewModel = tourDetailsViewModel;
    }



    public void setImage(byte[] image){
        System.out.println("image set");
        InputStream inputStream = new ByteArrayInputStream(image);
        tourImageView.setImage(new Image(inputStream));

    }
}
