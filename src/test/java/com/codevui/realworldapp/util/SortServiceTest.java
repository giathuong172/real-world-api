package com.codevui.realworldapp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SortServiceTest {
    @Test
    void testSort() {
        SortService sortService = new SortService();
        int numbers[] = new int[] { 1, 4, 3, 2, 5, 6, 8, 7, 9 };
        int[] resultExpect = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int[] resultActual = sortService.sort(numbers);

        for (int i = 0; i < resultActual.length; i++) {
            assertEquals(resultExpect[i], resultActual[i]);
        }
    }
}
