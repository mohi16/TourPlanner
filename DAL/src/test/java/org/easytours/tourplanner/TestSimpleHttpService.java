package org.easytours.tourplanner;

import org.easytours.tpmodel.Tour;
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
                "asdfs"
        );
    }

    private String getTourAsJson() {
        return "{\"name\":\"Tourname\",\"description\":\"Description\",\"from\":\"afsdf\",\"to\":\"asdfasdf\"," +
                "\"distance\":100,\"estTime\":3665,\"transportType\":\"asdf\",\"routeInfo\":\"asdfs\"}";
    }

    @Test
    public void testAddTour() {
        Tour tour = getTour();
        when(httpHandler.sendRequest("/add/", HttpMethod.POST, tour))
                .thenReturn(new MockHttpResponse<>(HttpStatusCode.CREATED));

        try {
            httpService.addTour(tour);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        verify(httpHandler).sendRequest("/add/", HttpMethod.POST, tour);
    }

    @Test
    public void testDeleteTour(){
        String tourname = "Tourname";
        when(httpHandler.sendRequest("/delete/Tourname/", HttpMethod.DELETE))
                .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

        try{
            httpService.deleteTour(tourname);
        }   catch(Exception e){
            e.printStackTrace();
            fail();
        }

        verify(httpHandler).sendRequest("/delete/Tourname/", HttpMethod.DELETE);
    }

    @Test
    public void testEditTour() {
        Tour tour = getTour();
        String tourname = "Tourname";
        when(httpHandler.sendRequest("/edit/Tourname/", HttpMethod.PUT, tour))
                .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK));

        try{
            httpService.editTour(tourname, tour);
        }   catch(Exception e){
            e.printStackTrace();
            fail();
        }

        verify(httpHandler).sendRequest("/edit/Tourname/", HttpMethod.PUT, tour);
    }

    @Test
    public void testGetTour(){
        String tourname = "Tourname";
        when(httpHandler.sendRequest("/tours/Tourname/", HttpMethod.GET))
                .thenReturn(new MockHttpResponse<>(HttpStatusCode.OK, getTourAsJson()));
        Tour expectedTour = getTour();

        Tour tour = null;
        try{
            tour = httpService.getTour(tourname);
        }   catch(Exception e){
            e.printStackTrace();
            fail();
        }

        verify(httpHandler).sendRequest("/tours/Tourname/", HttpMethod.GET);
        assertEquals(expectedTour, tour);
    }
}

