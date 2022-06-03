package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.easytours.tpmodel.TourLog;
import org.easytours.tpmodel.utils.TimeUtils;
import org.easytours.tpmodel.utils.Triple;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter

public class TourDetailsViewModel {
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty routeInfo = new SimpleStringProperty();
    private final StringProperty estTime = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();

    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    public void setDistance(double distance) {
        this.getDistance().set(String.valueOf(distance));

    }

    public void setEstTime(long estTime) {
        Triple<Integer, Integer, Integer> time = TimeUtils.deconstructTime(estTime);
        this.getEstTime().set(
                LocalTime.of(
                        time.getValue1(), time.getValue2(), time.getValue3()
                ).format(
                        DateTimeFormatter.ofPattern("H:m:s")
                )
        );
    }

    public final ObservableList<TourLog> tourLogsListProperty(){
        return tourLogs;
    }

    public void setTourLogs(TourLog[] tourLogs) {
        this.tourLogs.clear();
        this.tourLogs.addAll(tourLogs);
    }
}
