package com.rosenblat.richard.dto;

import com.rosenblat.richard.dto.geoDistance.Coordinates;
import com.rosenblat.richard.dto.googleApi.GeocodeResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DtoConverter {
    public static Coordinates geoCodeResponseToCoordinates(GeocodeResponse response) {

        Double lat = response.getResults().get(0).getGeometry().getLocation().getLat();
        Double lng = response.getResults().get(0).getGeometry().getLocation().getLng();

        return new Coordinates(lat, lng);
    }

}
