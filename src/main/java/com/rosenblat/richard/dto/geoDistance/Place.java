package com.rosenblat.richard.dto.geoDistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Place {
    private String address;
    private Coordinates coordinates;
}
