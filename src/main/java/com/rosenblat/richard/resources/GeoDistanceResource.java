package com.rosenblat.richard.resources;

import com.rosenblat.richard.bo.GeoDistanceBO;
import com.rosenblat.richard.dto.geoDistance.GeoDistanceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GeoDistanceResource {

    @Autowired
    GeoDistanceBO bo;

    @GetMapping("distance")
    public GeoDistanceResponse getDistance(@RequestParam String addresses) {
        log.info("Request recieved");
        return bo.getDistance(addresses);
    }
}
