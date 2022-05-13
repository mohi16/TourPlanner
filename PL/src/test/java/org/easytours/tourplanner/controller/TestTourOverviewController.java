package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

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
        //TourOverviewController controller = new TourOverviewController(viewModel);
        TourOverviewController controller = spy(new TourOverviewController(viewModel));

        try {
            when(controller.addTour()).thenReturn("stringdernichtleerist");
        } catch (IOException e) {
            fail();
        }

        int size = controller.getTourCount();

        controller.onAddTourButtonClick();

        assertEquals(size + 1, controller.getTourCount());

        //assertEquals(controller.getTours());
    }



    /*
    @Test
    public void testDeleteTour() {

    }
     */
}