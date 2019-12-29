package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestCountingSorting {
    @Test
    public void testCountingSort() {
        Assert.assertEquals(Sorting.countingSort(ar(1, 2, 1, 2, 2),
                1, 3), ar(1, 1, 2, 2, 2));


        Assert.assertEquals(Sorting.countingSort(ar(-1, -2, -1, -2, -2),
                -2, 0), ar(-2, -2, -2, -1, -1));

        int [][] arrays = {
                ar(1, 2, 3, 4, 5, 6),
                ar(0, 1, 0, 1, 0, 2, 0, 1),
                ar(0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
                ar(-10, -2, -4, 0, 2, 10, 15, 30)
        };

        for(var arr : arrays) {
            var copy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(copy);
            Assert.assertEquals(Sorting.countingSort(arr, -20, 40), copy);
        }
    }

    private static int[] ar(int... objects) {
        return objects;
    }
}
