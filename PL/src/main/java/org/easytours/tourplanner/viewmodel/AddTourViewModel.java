package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.easytours.tourplanner.AppConfig;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddTourViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty estTime = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty routeInfo = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getFrom() {
        return from.get();
    }

    public String getTo() {
        return to.get();
    }

    public double getDistance() {
        return Double.parseDouble(distance.get());
    }

    public LocalTime getEstTime() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(estTime.get(), AppConfig.getDateTimeFormatter());
    }

    public String getTransportType() {
        return transportType.get();
    }

    public String getRouteInfo() {
        return routeInfo.get();
    }

    public final StringProperty getNameProperty() {
        return name;
    }

    public final StringProperty getDescriptionProperty() {
        return description;
    }

    public final StringProperty getFromProperty() {
        return from;
    }

    public final StringProperty getToProperty() {
        return to;
    }

    public final StringProperty getDistanceProperty() {
        return distance;
    }

    public final StringProperty getEstTimeProperty() {
        return estTime;
    }

    public final StringProperty getTransportTypeProperty() {
        return transportType;
    }

    public final StringProperty getRouteInfoProperty() {
        return routeInfo;
    }
}
