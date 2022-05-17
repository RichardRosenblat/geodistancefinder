package com.rosenblat.richard;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.ExecutionException;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.rosenblat.richard.dto.geoDistance.Coordinates;
import com.rosenblat.richard.services.GeocodeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@WireMockTest(httpPort = 3030)
@TestPropertySource(properties = {"google.url=http://localhost:3030"})
public class ServiceTest {

    @Autowired
    GeocodeService service;
    
    @Test
    public void testGetCoordinates()
    {
        assertDoesNotThrow(() -> service.getCoordinates(TestsConsts.ADDRESS));
    }

    @Test
    public void testGetCoordinatesResponse() throws InterruptedException, ExecutionException
    {
        Coordinates response = service.getCoordinates(TestsConsts.ADDRESS).get();
        
        assertNotNull(response);
        assertNotEquals(new Coordinates(null, null), response);
    }
    

}