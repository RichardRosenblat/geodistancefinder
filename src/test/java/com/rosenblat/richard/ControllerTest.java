package com.rosenblat.richard;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.rosenblat.richard.dto.geoDistance.GeoDistanceResponse;
import com.rosenblat.richard.resources.GeoDistanceResource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@WireMockTest(httpPort = 3030)
@TestPropertySource(properties = {"google.url=http://localhost:3030"})
public class ControllerTest {

    @Autowired
    GeoDistanceResource resource;

    @Test
    public void testGetDistance()
    {
        assertDoesNotThrow(() -> resource.getDistance(TestsConsts.ADDRESS));
        
        GeoDistanceResponse response = resource.getDistance(TestsConsts.ADDRESS);
        
        assertNotNull(response);
        assertNotEquals(new GeoDistanceResponse(), response);
        assertEquals(3, response.getResults().size());
    }

}