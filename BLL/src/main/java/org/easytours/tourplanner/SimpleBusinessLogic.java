package org.easytours.tourplanner;



import org.easytours.tpmodel.Tour;

import java.net.ConnectException;

public class SimpleBusinessLogic implements BusinessLogic {
    private HttpService httpService;

    public SimpleBusinessLogic(HttpService httpService) {
        this.httpService = httpService;
    }

    /**
     * Add a new tour.
     *
     * @param tour the tour to add.
     * @throws IllegalArgumentException if the tour object is not valid.
     * @throws Exception if any other exception occurs, e.g. fail to connect to server or the tour already exists.
     */
    @Override
    public void addTour(Tour tour) throws Exception {
        if (!tour.isValid()) {
            throw new IllegalArgumentException("The tour is not valid");
        }

        httpService.addTour(tour);
    }

    @Override
    public void deleteTour(String name) throws Exception {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }

        httpService.deleteTour(name);
    }

    @Override
    public void editTour(String name, Tour newTour) throws Exception {
        if (null == name || name.isEmpty() || !newTour.isValid()) {
            throw new IllegalArgumentException("Name is null or empty or tour is not valid");
        }

        httpService.editTour(name, newTour);
    }

    @Override
    public Tour getTour(String name) throws Exception {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }

        return httpService.getTour(name);

        /*return new Tour(
                "Tourname",
                "Description",
                "afsdf",
                "asdfasdf",
                100,
                3665,
                "asdf",
                "asdfs"
        );*/
    }
}
