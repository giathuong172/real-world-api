package com.codevui.realworldapp.util;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomMath {

    private final SortService sort;

    public int getMinMax(int numbers[], boolean isFindMax) {
        int[] sortedNumbers = sort.sort(numbers);
        if (isFindMax) {
            return sortedNumbers[sortedNumbers.length - 1];
        } else {
            return sortedNumbers[0];
        }
    }

}
