package org.easytours.tourplanner;

import org.easytours.tourplanner.model.Tour;

public interface BusinessLogic {
    void addTour(Tour tour);
    void deleteTour(String name);
    void editTour(String name, Tour newTour);
}
