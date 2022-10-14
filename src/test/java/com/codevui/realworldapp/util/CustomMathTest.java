package com.codevui.realworldapp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomMathTest {

    @InjectMocks
    private CustomMath customMath;

    @Mock
    SortService sort;

    @Test
    void testGetMinMax_Return_Max_Value() {

        // given
        int numbers[] = new int[] { 1, 3, 4, 5, 6, 2, 7, 9, 8 };
        boolean isFindMax = true;
        // when

        when(sort.sort(numbers)).thenReturn(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

        // then
        int actual = customMath.getMinMax(numbers, isFindMax);

        assertEquals(9, actual);
    }
}
