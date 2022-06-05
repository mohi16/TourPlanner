package org.easytours.tourplanner;



import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    }

    @Override
    public String[] getTourNames() throws Exception {
        return httpService.getTourNames();
    }

    @Override
    public Tour getTourWithImage(String name) throws Exception {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }

        return httpService.getTourWithImage(name);
    }

    @Override
    public int addTourLog(String tourName, TourLog tourLog) throws Exception {
        if (!tourLog.isValid() || null == tourName || tourName.isEmpty()) {
            throw new IllegalArgumentException("The tour is not valid");
        }
        return httpService.addTourLog(tourName, tourLog);

    }

    public void deleteTourLog(int id) throws Exception {
        httpService.deleteTourLog(id);
    }

    public void editTourLog(int id, TourLog newTourLog) throws Exception {
        if (!newTourLog.isValid()) {
            throw new IllegalArgumentException("The tour log is not valid");
        }

        httpService.editTourLog(id, newTourLog);
    }

    public TourLog getTourLog(int id) throws Exception {
        return httpService.getTourLog(id);
    }

    @Override
    public void generateSingleReport(File file, String tourname) throws Exception {
        if (null == file || null == tourname || tourname.isEmpty()) {
            throw new IllegalArgumentException("file or name is null");
        }

        byte[] pdfBytes = httpService.generateSingleReport(tourname);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(pdfBytes);
        fos.close();
    }

    @Override
    public void generateSummaryReport(File file) throws Exception {
        if (null == file) {
            throw new IllegalArgumentException("file or name is null");
        }

        byte[] pdfBytes = httpService.generateSummaryReport();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(pdfBytes);
        fos.close();
    }

    @Override
    public void importTours(File file) throws Exception {
        if (null == file) {
            throw new IllegalArgumentException("file is null");
        }

        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        fis.close();
        httpService.importTours(new String(bytes));
    }

    @Override
    public void exportTours(File file) throws Exception {
        if (null == file) {
            throw new IllegalArgumentException("file is null");
        }

        byte[] bytes = httpService.exportTours().getBytes();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    @Override
    public String[] getTourNames(String filter) throws Exception {
        if (null == filter) {
            return httpService.getTourNames();
        } else {
            return httpService.getTourNames(filter);
        }
    }
}
