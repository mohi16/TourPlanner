package org.easytours.tourplanner;

import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;
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
            "asdfs",
            ""
        );
    }

    private TourLog getTourLog() {
        return new TourLog(
                "date",
                "comment",
                4,
                23,
                2
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
            "asdfs",
            ""
        );
    }

    private TourLog getBadTourLog() {
        return new TourLog(
                "date",
                "comment",
                4,
                34,
                6
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
    
    @Test
    public void testGetTourNames(){
        String[] expectedTourNames = new String[]{"Tour1", "Tour2", "Tour3"};
        try{
            when(httpService.getTourNames()).thenReturn(expectedTourNames);
        } catch(Exception e){
            fail();
        }

        String[] tournames = null;
        try {
            tournames = bl.getTourNames();
        } catch(Exception e){
            fail();
        }

        try {
            verify(httpService).getTourNames();
        } catch(Exception e){
            fail();
        }
        assertArrayEquals(expectedTourNames, tournames);
    }

    @Test
    public void testGetTourNamesFail(){
        try{
            when(httpService.getTourNames()).thenThrow(new Exception());
        } catch(Exception e){
            fail();
        }

        try {
            bl.getTourNames();
            fail();
        } catch(Exception e){
            //assertTrue
        }

        try {
            verify(httpService).getTourNames();
        } catch(Exception e){
            fail();
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddTourLog() {
        String tourname = "Tourname";
        TourLog tourLog = getTourLog();
        int expectedId = 1001;
        try {
            when(httpService.addTourLog(tourname, tourLog)).thenReturn(expectedId);
        } catch (Exception e) {
           fail();
        }

        int id = 0;
        try {
            id = bl.addTourLog(tourname, tourLog);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            verify(httpService).addTourLog(tourname, tourLog);
            assertEquals(expectedId, id);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddTourLogFail() {
        String tourname = "Tourname";
        TourLog tourLog = getBadTourLog();
        boolean isThrown = false;

        try {
            bl.addTourLog(tourname, tourLog);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).addTourLog(tourname, tourLog);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTourLog() {
        int id = 1001;

        try {
            bl.deleteTourLog(id);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).deleteTourLog(id);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEditTourLog() {
        int id = 1001;
        TourLog newTourLog = getTourLog();

        try {
            bl.editTourLog(id, newTourLog);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).editTourLog(id, newTourLog);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEditTourLogFail(){
        int id = 1001;
        TourLog newTourLog = getBadTourLog();
        boolean isThrown = false;

        try {
            bl.editTourLog(id, newTourLog);
        } catch (IllegalArgumentException e) {
            isThrown = true;
        } catch (Exception e) {
            fail();
        }

        assertTrue(isThrown);
        try {
            verify(httpService, times(0)).editTourLog(id, newTourLog);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetTourLog() {
        int id = 1001;
        TourLog expectedTourLog = getTourLog();
        //expectedTourLog.setDistance(100);
        //expectedTourLog.setEstTime(3665);
        try {
            when(httpService.getTourLog(id)).thenReturn(expectedTourLog);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        TourLog tourLog = null;
        try {
            tourLog = bl.getTourLog(id);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(httpService).getTourLog(id);
        } catch (Exception e) {
            fail();
        }
        assertEquals(expectedTourLog, tourLog);
    }

    @Test
    public void testGetTourLogFail() {
        int id = 1001;
        boolean isThrown = false;
        try {
            when(httpService.getTourLog(id)).thenThrow(new Exception());
        } catch (Exception e) {
            fail();
        }

        TourLog tourLog = null;
        try {
            tourLog = bl.getTourLog(id);
        } catch (Exception e) {
            isThrown = true;
        }

        assertTrue(isThrown);
        try {
            verify(httpService).getTourLog(id);
        } catch (Exception e) {
            fail();
        }
    }

}