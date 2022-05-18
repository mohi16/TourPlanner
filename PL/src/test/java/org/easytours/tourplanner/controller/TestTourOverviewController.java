package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;
import org.easytours.tourplanner.model.Tour;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TestTourOverviewController {
    private TourOverviewViewModel viewModel;
    private AddTourDialogHandler addTourDialogHandler;



    @BeforeEach
    public void init() {
        addTourDialogHandler = mock(AddTourDialogHandler.class);
    }

    @Test
    public void testAddTour() {
        TourOverviewViewModel tourOverviewViewModel = new TourOverviewViewModel();
        TourOverviewController controller = new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);

        when(addTourDialogHandler.createTour()).thenReturn(
                new Tour(
                        "Tour1",
                        "",
                        "From",
                        "To",
                        10.5,
                        LocalTime.parse("20:22:21", AppConfig.getDateTimeFormatter()),
                        "Transport Type",
                        "Route Info"
                )
        );

        int size = tourOverviewViewModel.toursListProperty().size();

        controller.onAddTourButtonClick();

        assertEquals(size + 1, tourOverviewViewModel.toursListProperty().size());
        assertEquals("Tour1", tourOverviewViewModel.toursListProperty().get(0));
    }

    @Test
    public void testAddTourFail() {
        TourOverviewViewModel tourOverviewViewModel = new TourOverviewViewModel();
        TourOverviewController controller = new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);

        when(addTourDialogHandler.createTour()).thenReturn(null);

        controller.onAddTourButtonClick();

        assertEquals(0, tourOverviewViewModel.toursListProperty().size());
    }



    /*
    @Test
    public void testDeleteTour() {

    }
     */
}
