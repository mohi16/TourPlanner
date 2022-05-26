package org.easytours.tourplanner;

import org.easytours.tpmodel.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestSimpleBusinessLogic {
    private HttpService httpService;
    private BusinessLogic bl;

    @BeforeEach
    public void init() {
        httpService = mock(HttpService.class);
        bl = new SimpleBusinessLogic(httpService);
    }

    private Tour getTour() {
        return new Tour(
            "Tourname",
            "Description",
            "afsdf",
            "asdfasdf",
            0,
            0,
            "asdf",
            "asdfs"
        );
    }

    private Tour getBadTour() {
        return new Tour(
                "Tourname",
                "Description",
                "",
                "asdfasdf",
                0,
                0,
                "asdf",
                "asdfs"
        );
    }

    @Test
    public void testAddTour() {
        Tour tour = getTour();

        try {
            bl.addTour(tour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            verify(httpService).addTour(tour);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddTourFail() {
        Tour tour = getBadTour();
        boolean isThrown = false;

        try {
            bl.addTour(tour);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).addTour(tour);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTour() {
        String tourname = "Tourname";

        try {
            bl.deleteTour(tourname);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).deleteTour(tourname);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTourFail() {
        String tourname = "";
        boolean isThrown = false;

        try {
            bl.deleteTour(tourname);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).deleteTour(tourname);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEditTour() {
        String tourname = "Tourname";
        Tour newTour = getTour();

        try {
            bl.editTour(tourname, newTour);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).editTour(tourname, newTour);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEditTourFail(){
        String tourname = "Tourname";
        Tour newTour = getBadTour();
        boolean isThrown = false;

        try {
            bl.editTour(tourname, newTour);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).editTour(tourname, newTour);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetTour() {
        String tourname = "Tourname";
        Tour expectedTour = getTour();
        expectedTour.setDistance(100);
        expectedTour.setEstTime(3665);
        try {
            when(httpService.getTour(tourname)).thenReturn(expectedTour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        Tour tour = null;
        try {
            tour = bl.getTour(tourname);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).getTour(tourname);
        } catch (Exception e) {
            fail();
        }
        assertEquals(expectedTour, tour);
    }

    @Test
    public void testGetTourFail() {
        String tourname = "";
        boolean isThrown = false;

        Tour tour = null;
        try {
            tour = bl.getTour(tourname);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).getTour(tourname);
        } catch (Exception e) {
            fail();
        }
    }
}