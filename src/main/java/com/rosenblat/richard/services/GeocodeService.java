package com.rosenblat.richard.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import com.rosenblat.richard.config.ConfigProperties;
import com.rosenblat.richard.dto.DtoConverter;
import com.rosenblat.richard.dto.geoDistance.Coordinates;
import com.rosenblat.richard.dto.googleApi.GeocodeResponse;
import com.rosenblat.richard.util.JsonBodyHandler;
import com.rosenblat.richard.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class GeocodeService {

    @Autowired
    ConfigProperties config;

    @Async
    public Future<Coordinates> getCoordinates(String address) {

        log.info("Getting coordinates for address: {}", address);

        HttpRequest request = getGeocodeRequest(address);

        GeocodeResponse response;

        try {
            response = getCheckedResponse(request);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while sending request", e);
        }

        Coordinates coordinates = DtoConverter.geoCodeResponseToCoordinates(response);
        
        return new AsyncResult<Coordinates>(coordinates);

    }

    private GeocodeResponse getCheckedResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<Supplier<GeocodeResponse>> response = sendRequest(request);

        GeocodeResponse geocoderesponse = checkGeocodeResponse(response);

        return geocoderesponse;
    }

    private GeocodeResponse checkGeocodeResponse(HttpResponse<Supplier<GeocodeResponse>> response) {
        ResponseUtil.checkResponseOk(response);

        GeocodeResponse geocoderesponse = response.body().get();

        ResponseUtil.checkResultsSize(geocoderesponse);
        return geocoderesponse;
    }

    public HttpResponse<Supplier<GeocodeResponse>> sendRequest(HttpRequest request)
            throws IOException, InterruptedException {

        HttpResponse<Supplier<GeocodeResponse>> model = HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(GeocodeResponse.class));

        return model;
    }

    private HttpRequest getGeocodeRequest(String address) {
        log.info("Getting request to: " + config.getUrl());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        config.getUrl() + "/maps/api/geocode/json?address=" + address.replaceAll("\\s+","%20") + "&key=" + config.getKey()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return request;
    }

}
