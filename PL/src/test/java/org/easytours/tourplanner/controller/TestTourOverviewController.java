package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TestTourOverviewController {
    private TourOverviewViewModel viewModel;



    @BeforeEach
    public void init() {
        TourOverviewViewModel viewModel = mock(TourOverviewViewModel.class);
    }

    @Test
    public void testAddTour() {
        TourOverviewController controller = new TourOverviewController(viewModel);

        controller.onAddTourButtonClick();

        //assertEquals(controller.getTours());
    }

    @Test
    public void testDeleteTour() {

    }
}
