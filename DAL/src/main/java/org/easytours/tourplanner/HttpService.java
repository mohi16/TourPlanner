package org.easytours.tourplanner;


import org.easytours.tpmodel.Tour;

public interface HttpService {
    void addTour(Tour tour) throws Exception;
    void deleteTour(String name) throws Exception;
    void editTour(String name, Tour newTour) throws Exception;
    Tour getTour(String name) throws Exception;
}
