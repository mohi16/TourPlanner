package org.easytours.tourplanner;


import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;

public interface BusinessLogic {
    //Tour
    void addTour(Tour tour) throws Exception;
    void deleteTour(String name) throws Exception;
    void editTour(String name, Tour newTour) throws Exception;
    Tour getTour(String name) throws Exception;
    String[] getTourNames() throws Exception;
    Tour getTourWithImage(String name) throws Exception;

    //TourLog
    int addTourLog(String tourName, TourLog tourlog) throws Exception;
    void deleteTourLog(int id) throws Exception;
    void editTourLog(int id, TourLog newTourLog) throws Exception;
    TourLog getTourLog(int id) throws Exception;
}
