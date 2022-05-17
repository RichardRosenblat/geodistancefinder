package com.rosenblat.richard.bo;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.rosenblat.richard.dto.geoDistance.GeoDistanceResponse;
import com.rosenblat.richard.dto.geoDistance.Place;
import com.rosenblat.richard.dto.geoDistance.PlaceMatch;
import com.rosenblat.richard.services.GeocodeService;
import com.rosenblat.richard.util.CombinationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeoDistanceBO {

    @Autowired
    GeocodeService geoCodeService;

    public GeoDistanceResponse getDistance(String addresses) {

        log.info("getting distance from addresses given");

        Set<String> addressesSet = Arrays.asList(addresses.split(";+[ ]*"))
                .stream().collect(Collectors.toSet());

        checkAmoutOfAddresses(addressesSet);

        Set<Place> placesSet = addressesSet.stream().map((x) -> mapAddressToPlace(x)).collect(Collectors.toSet());

        Set<PlaceMatch> matches = CombinationUtil.getCombinations(placesSet, placesSet.size(), 2);

        return new GeoDistanceResponse(matches);
    }
    
    private void checkAmoutOfAddresses(Set<String> addressesSet) {
        if (addressesSet.size() < 2) {
            log.info("only {} addresses given, the minimum is 2", addressesSet.size());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough addresses.");
        }
    }

    private Place mapAddressToPlace(String address) {
        try {
            return new Place(address, geoCodeService.getCoordinates(address).get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Thread error while getting coordinates", e);
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thread error while getting coordinates",
                    e);
        }
    }

}
