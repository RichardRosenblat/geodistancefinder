package com.rosenblat.richard.dto.geoDistance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PlaceMatch {

    private Place firstAddress;
    private Place secondAddress;

    private double distance;

    public void setFirstAddress(Place firstPlace) {
        this.firstAddress = firstPlace;
        setDistance();
    }

    public void setSecondAddress(Place secondPlace) {
        this.secondAddress = secondPlace;
        setDistance();
    }

    private void setDistance() {

        if (firstAddress == null || secondAddress == null) {
            return;
        }

        if (firstAddress.getCoordinates() == null || secondAddress.getCoordinates() == null) {
            return;
        }

        double x1 = firstAddress.getCoordinates().getLatitude();
        double y1 = firstAddress.getCoordinates().getLongitude();

        double x2 = secondAddress.getCoordinates().getLatitude();
        double y2 = secondAddress.getCoordinates().getLongitude();

        this.distance = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    public PlaceMatch(Place firstPlace, Place secondPlace) {
        this.firstAddress = firstPlace;
        this.secondAddress = secondPlace;

        setDistance();
    }
}
