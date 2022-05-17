package com.rosenblat.richard.dto.geoDistance;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@JsonInclude(Include.NON_NULL)
public class GeoDistanceResponse {

   private Set<PlaceMatch> closestMatch;
   private Set<PlaceMatch> furthestMatch;

   private Set<PlaceMatch> results = new HashSet<PlaceMatch>();

   public void setResults(Set<PlaceMatch> results) {
      this.results = results;
      findClosest();
      findFurthest();
   }

   public GeoDistanceResponse(Set<PlaceMatch> results) {
      this.results = results;
      findClosest();
      findFurthest();
   }

   private void findClosest() {
      if (results.size() == 1){
         return;
      }
      
      Set<PlaceMatch> closestSet = new HashSet<>();
      PlaceMatch closest = null;

      for (PlaceMatch placeMatch : results) {
         if (closest == null) {
            closest = placeMatch;
         }
         if (closest.getDistance() > placeMatch.getDistance()) {
            closest = placeMatch;
         }
      }

      closestSet.add(closest);

      for (PlaceMatch placeMatch : results) {
         if (closest.getDistance() == placeMatch.getDistance()) {
            closestSet.add(placeMatch);
         }
      }

      this.closestMatch = closestSet;
   }

   private void findFurthest() {
      if (results.size() == 1){
         return;
      }

      Set<PlaceMatch> furthestSet = new HashSet<>();
      PlaceMatch furthest = null;

      for (PlaceMatch placeMatch : results) {
         if (furthest == null) {
            furthest = placeMatch;
         }
         if (placeMatch.getDistance() > furthest.getDistance()) {
            furthest = placeMatch;
         }
      }

      furthestSet.add(furthest);

      for (PlaceMatch placeMatch : results) {
         if (furthest.getDistance() == placeMatch.getDistance()) {
            furthestSet.add(placeMatch);
         }
      }

      this.furthestMatch = furthestSet;
   }

}
