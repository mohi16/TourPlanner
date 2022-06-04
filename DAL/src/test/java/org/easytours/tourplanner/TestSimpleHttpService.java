package org.easytours.tourplanner;

import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;
import org.easytours.tpmodel.http.HttpMethod;
import org.easytours.tpmodel.http.HttpStatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestSimpleHttpService {
    private HttpHandler httpHandler;
    private HttpService httpService;

    @BeforeEach
    public void init() {
        httpHandler = mock(HttpHandler.class);
        httpService = new SimpleHttpService(httpHandler);
    }

    private Tour getTour() {
        return new Tour(
                "Tourname",
                "Description",
                "afsdf",
                "asdfasdf",
                100,
                3665,
                "asdf",
                "asdfs",
                ""
        );
    }

    private TourLog getTourLog() {
        return new TourLog(
                "2022-04-06T12:13:14",
                "Some Comment",
                2,
                2005,
                4
        );
    }

    private String getTourAsJson() {
        return "{\"name\":\"Tourname\",\"description\":\"Description\",\"from\":\"afsdf\",\"to\":\"asdfasdf\"," +
                "\"distance\":100,\"estTime\":3665,\"transportType\":\"asdf\",\"routeInfo\":\"asdfs\"}";
    }

    private String getTourNamesAsJson(){
        return "[\"Tour1\", \"Tour2\", \"Tour3\"]";
    }

    private String getTourLogAsJson() {
        return "{\"dateTime\":\"2022-04-06T12:13:14\",\"comment\":\"Some Comment\",\"difficulty\":2,\"totalTime\":2005,\"rating\":4}";
    }



    @Test
    public void testAddTour() {
        Tour tour = getTour();
        try {
            when(httpHandler.sendRequest("/add/", HttpMethod.POST, tour))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.CREATED));

            httpService.addTour(tour);

            verify(httpHandler).sendRequest("/add/", HttpMethod.POST, tour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteTour() {
        String tourname = "Tourname";
        try {
            when(httpHandler.sendRequest("/delete/Tourname/", HttpMethod.DELETE))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

            httpService.deleteTour(tourname);

            verify(httpHandler).sendRequest("/delete/Tourname/", HttpMethod.DELETE);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEditTour() {
        Tour tour = getTour();
        String tourname = "Tourname";
        try {
            when(httpHandler.sendRequest("/edit/Tourname/", HttpMethod.PUT, tour))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

            httpService.editTour(tourname, tour);

            verify(httpHandler).sendRequest("/edit/Tourname/", HttpMethod.PUT, tour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetTour() {
        String tourname = "Tourname";
        Tour expectedTour = getTour();
        try {
            when(httpHandler.sendRequest("/tours/Tourname/", HttpMethod.GET))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK, getTourAsJson()));

            Tour tour = httpService.getTour(tourname);

            verify(httpHandler).sendRequest("/tours/Tourname/", HttpMethod.GET);
            assertEquals(expectedTour, tour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetTourNames() {
        String[] expectedTourNames = new String[]{"Tour1", "Tour2", "Tour3"};
        try {
            when(httpHandler.sendRequest("/tournames/", HttpMethod.GET))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK, getTourNamesAsJson()));

            String[] tournames = httpService.getTourNames();

            verify(httpHandler).sendRequest("/tournames/", HttpMethod.GET);
            assertArrayEquals(expectedTourNames, tournames);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAddTourLog() {
        String tourname = "Tourname";
        TourLog tourLog = getTourLog();
        int expectedId = 1001;
        try {
            when(httpHandler.sendRequest("/addLog/Tourname/", HttpMethod.POST, tourLog))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.CREATED, "1001"));


            int id = httpService.addTourLog(tourname, tourLog);

            verify(httpHandler).sendRequest("/addLog/Tourname/", HttpMethod.POST, tourLog);
            assertEquals(expectedId, id);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteTourLog() {
        int id = 1001;
        try {
            when(httpHandler.sendRequest("/deleteLog/1001/", HttpMethod.DELETE))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

            httpService.deleteTourLog(id);

            verify(httpHandler).sendRequest("/deleteLog/1001/", HttpMethod.DELETE);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEditTourLog() {
        TourLog tourLog = getTourLog();
        int id = 1001;
        try {
            when(httpHandler.sendRequest("/editLog/1001/", HttpMethod.PUT, tourLog))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

            httpService.editTourLog(id, tourLog);

            verify(httpHandler).sendRequest("/editLog/1001/", HttpMethod.PUT, tourLog);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetTourLog() {
        int id = 1001;
        TourLog expectedTourLog = getTourLog();
        try {
            when(httpHandler.sendRequest("/logs/1001/", HttpMethod.GET))
                    .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK, getTourLogAsJson()));

            TourLog tourLog = httpService.getTourLog(id);

            verify(httpHandler).sendRequest("/logs/1001/", HttpMethod.GET);
            assertEquals(expectedTourLog, tourLog);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

