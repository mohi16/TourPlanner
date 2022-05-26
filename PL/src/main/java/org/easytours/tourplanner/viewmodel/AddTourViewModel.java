package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddTourViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    //private final StringProperty distance = new SimpleStringProperty();
    //private final StringProperty estTimeHours = new SimpleStringProperty();
    //private final StringProperty estTimeMins = new SimpleStringProperty();
    //private final StringProperty estTimeSecs = new SimpleStringProperty();
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

    /*public double getDistance() {
        return Double.parseDouble(distance.get());
    }*/

    /*public int getEstTimeHours() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return Integer.parseInt(estTimeHours.get());
    }*/

    /*public int getEstTimeMins() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        //return LocalTime.parse(estTime.get(), AppConfig.getDateTimeFormatter());
        return Integer.parseInt(estTimeMins.get());
    }*/

    /*public int getEstTimeSecs() {
        return Integer.parseInt(estTimeSecs.get());
    }*/

    public String getTransportType(){
        return transportType.get();
    }

    public String getRouteInfo(){
        return routeInfo.get();
    }

    //Setter
    public void setName(String name) {
        this.name.set(name);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    /*
    public void setDistance(String distance ) {
        this.distance.set(distance);
    }

    public void setEstTimeHours(String estTimeHours) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.estTimeHours.set(estTimeHours);
    }

    public void setEstTimeMins(String estTimeMins) {
        this.estTimeMins.set(estTimeMins);
    }

    public void setEstTimeSecs(String estTimeSecs) {
        this.estTimeSecs.set(estTimeSecs);
    }
    */

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo.set(routeInfo);
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

    /*public final StringProperty getDistanceProperty() {
        return distance;
    }

    public final StringProperty getEstTimeHoursProperty() {
        return estTimeHours;
    }

    public final StringProperty getEstTimeMinsProperty() {
        return estTimeMins;
    }

    public final StringProperty getEstTimeSecsProperty() {
        return estTimeSecs;
    }*/

    public final StringProperty getTransportTypeProperty() {
        return transportType;
    }

    public final StringProperty getRouteInfoProperty() {
        return routeInfo;
    }
}
