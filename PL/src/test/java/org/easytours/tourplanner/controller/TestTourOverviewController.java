package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.App;
import org.easytours.tourplanner.AppConfig;
import org.easytours.tourplanner.BusinessLogic;
import org.easytours.tourplanner.SimpleBusinessLogic;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;
import org.easytours.tourplanner.utils.TimeUtils;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.easytours.tpmodel.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TestTourOverviewController {
    private TourOverviewViewModel viewModel;
    private AddTourDialogHandler addTourDialogHandler;
    private BusinessLogic businessLogic;



    /*
    @BeforeEach
    public void init() {
        addTourDialogHandler = mock(AddTourDialogHandler.class);
        businessLogic = mock(SimpleBusinessLogic.class);
        App.setBusinessLogic(businessLogic);
    }

    @Test
    public void testAddTour() throws Exception {
        TourOverviewViewModel tourOverviewViewModel = new TourOverviewViewModel();
        TourOverviewController controller = new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);

        when(addTourDialogHandler.createTour()).thenReturn(
                new Tour(
                        "Tour1",
                        "",
                        "From",
                        "To",
                        10.5,
                        TimeUtils.constructTime(20, 22, 21),
                        "Transport Type",
                        "Route Info"
                )
        );

        Tour tour = new Tour(
                "Tour1",
                "",
                "From",
                "To",
                10.5,
                TimeUtils.constructTime(20, 22, 21),
                "Transport Type",
                "Route Info"
        );

        when(businessLogic.addTour(tour)).thenReturn(true);

        int size = tourOverviewViewModel.toursListProperty().size();

        controller.onAddTourButtonClick();



        verify(businessLogic).addTour(tour);
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

     */

    /*
    @Test
    public void testDeleteTour() {
        TourOverviewViewModel tourOverviewViewModel = new TourOverviewViewModel();
        TourOverviewController controller = new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);

        when(businessLogic.deleteTour("Tour 1")).thenReturn(true);

        controller.onDeleteTourButtonClick();

        verify(businessLogic).deleteTour("Tour 1");
        assertEquals(2, tourOverviewViewModel.toursListProperty().size());
        assertEquals("Tour 2", tourOverviewViewModel.toursListProperty().get(0));
    }
    */
}
