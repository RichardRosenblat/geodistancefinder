package com.rosenblat.richard.util;

import java.util.HashSet;
import java.util.Set;

import com.rosenblat.richard.dto.geoDistance.Place;
import com.rosenblat.richard.dto.geoDistance.PlaceMatch;

import lombok.NoArgsConstructor;

// Java program to print all combination of size r in an array of size n
@NoArgsConstructor
public class CombinationUtil {

    /*
     * arr[] ---> Input Array
     * data[] ---> Temporary array to store current combination
     * start & end ---> Starting and Ending indexes in arr[]
     * index ---> Current index in data[]
     * r ---> Size of a combination to be printed
     */
    private static void doCombinations(Place arr[], Place data[], int start,
            int end, int index, int r, Set<PlaceMatch> out) {
        
        // Current combination is ready to be added to output, add it
        if (index == r) {
            PlaceMatch match = new PlaceMatch();

            // flag for adding the first adress first then the second
            boolean isFirstAddress = true;
            for (int j = 0; j < r; j++) {
                if(isFirstAddress){
                    match.setFirstAddress(data[j]);
                }
                else{
                    match.setSecondAddress(data[j]);
                }
                isFirstAddress = !isFirstAddress;
            }

            out.add(match);
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            doCombinations(arr, data, i + 1, end, index + 1, r, out);
        }
    }


    // The main function that prints all combinations of size r
    // in Set<AddressMatch> of size n. This function mainly uses combinationUtil()
    public static Set<PlaceMatch> getCombinations(Set<Place> set, int sizeOfSet, int sizeOfCombination) {
       
        // Converts the set to an array
        Place arr[] = new Place[set.size()];
        arr = set.toArray(arr);

        // A temporary array to store all combination one by one
        Place data[] = new Place[sizeOfCombination];
        
        // Result set with combinations in placematches
        Set<PlaceMatch> combinations = new HashSet<>();

        // Print all combination using temporary array 'data[]'
        doCombinations(arr, data, 0, sizeOfSet - 1, 0, sizeOfCombination, combinations);

        return combinations;
    }
}

/* This code is contributed by Devesh Agrawal adapted by Richard Rosenblat */
