package org.easytours.tourplanner.model;

import java.time.LocalTime;

public class Tour {
    private String name;
    private String description;
    private String from;
    private String to;
    private double distance;
    private LocalTime estTime;
    private String transportType;
    private String routeInfo;

    public Tour(
            String name,
            String description,
            String from,
            String to,
            double distance,
            LocalTime estTime,
            String transportType,
            String routeInfo
    ) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.estTime = estTime;
        this.transportType = transportType;
        this.routeInfo = routeInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LocalTime getEstTime() {
        return estTime;
    }

    public void setEstTime(LocalTime estTime) {
        this.estTime = estTime;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tour) {
            return equals((Tour) other);
        } else {
            return false;
        }
    }

    private boolean isSame(Object left, Object right) {
        if (null == left && null == right) {
            return true;
        } else if (null != left){
            return left.equals(right);
        } else {
            return false;
        }
    }

    public boolean equals(Tour other) {
        return isSame(name, other.name) &&
                isSame(description, other.description) &&
                isSame(from, other.from) &&
                isSame(to, other.to) &&
                distance == other.distance &&
                isSame(estTime, other.estTime) &&
                isSame(transportType, other.transportType) &&
                isSame(routeInfo, other.routeInfo);
    }
}
