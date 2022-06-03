package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.easytours.tourplanner.viewmodel.AddTourLogViewModel;
import org.easytours.tpmodel.TourLog;
import org.easytours.tpmodel.utils.TimeUtils;
import org.easytours.tpmodel.utils.Triple;

public class AddTourLogController {
    private final AddTourLogViewModel addTourLogViewModel;

    public AddTourLogController(AddTourLogViewModel addTourLogViewModel){
        this.addTourLogViewModel = addTourLogViewModel;
    }

    @FXML
    private TextField dateTime;

    @FXML
    private TextArea comment;

    @FXML
    private TextField difficulty;

    @FXML
    private TextField totalTime;

    @FXML
    private TextField totalTimeHours;

    @FXML
    private TextField totalTimeMinutes;

    @FXML
    private TextField totalTimeSeconds;

    @FXML
    private TextField rating;

    @FXML
    public void initialize() {
        dateTime.textProperty().bindBidirectional(addTourLogViewModel.getDateTime());
        comment.textProperty().bindBidirectional(addTourLogViewModel.getComment());
        difficulty.textProperty().bindBidirectional(addTourLogViewModel.getDifficultyProperty());
        totalTimeHours.textProperty().bindBidirectional(addTourLogViewModel.getTotalTimeHoursProperty());
        totalTimeMinutes.textProperty().bindBidirectional(addTourLogViewModel.getTotalTimeMinutesProperty());
        totalTimeSeconds.textProperty().bindBidirectional(addTourLogViewModel.getTotalTimeSecondsProperty());
        rating.textProperty().bindBidirectional(addTourLogViewModel.getRatingProperty());
    }

    public void clear() {
        addTourLogViewModel.setDateTime("");
        addTourLogViewModel.setComment("");
        addTourLogViewModel.getDifficultyProperty().set("");
        addTourLogViewModel.getTotalTimeHoursProperty().set("");
        addTourLogViewModel.getTotalTimeMinutesProperty().set("");
        addTourLogViewModel.getTotalTimeSecondsProperty().set("");
        addTourLogViewModel.getRatingProperty().set("");
    }

    public void fill(TourLog tourLog) {
        addTourLogViewModel.setDateTime(tourLog.getDateTime());
        addTourLogViewModel.setComment(tourLog.getComment());
        addTourLogViewModel.setDifficulty(tourLog.getDifficulty());
        Triple<Integer, Integer, Integer> time = TimeUtils.deconstructTime(tourLog.getTotalTime());
        addTourLogViewModel.setTotalTimeHours(time.getValue1());
        addTourLogViewModel.setTotalTimeMinutes(time.getValue2());
        addTourLogViewModel.setTotalTimeSeconds(time.getValue3());
        addTourLogViewModel.setRating(tourLog.getRating());
    }

    public TourLog getTourLog() {
        return new TourLog(
                addTourLogViewModel.getDateTime().get(),
                addTourLogViewModel.getComment().get(),
                addTourLogViewModel.getDifficulty(),
                TimeUtils.constructTime(
                        addTourLogViewModel.getTotalTimeHours(),
                        addTourLogViewModel.getTotalTimeMinutes(),
                        addTourLogViewModel.getTotalTimeSeconds()
                ),
                addTourLogViewModel.getRating()
        );
    }
}
