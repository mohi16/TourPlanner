package org.easytours.tourplanner;

import org.easytours.tpmodel.Tour;
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

    private String getTourAsJson() {
        return "{\"name\":\"Tourname\",\"description\":\"Description\",\"from\":\"afsdf\",\"to\":\"asdfasdf\"," +
                "\"distance\":100,\"estTime\":3665,\"transportType\":\"asdf\",\"routeInfo\":\"asdfs\"}";
    }

    private String getTourNamesAsJson(){
        return "[\"Tour1\", \"Tour2\", \"Tour3\"]";
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
}

