package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestMergeSorting {
    @Test
    public void testMergeSort() {
        Assert.assertEquals(asc(6, 5, 9, 3, 2, 1), ar(1, 2, 3, 5, 6, 9));
        Assert.assertEquals(asc(1, 1, 1, 1, 2, 1), ar(1, 1, 1, 1, 1, 2));
        Assert.assertEquals(asc(2, 1, 1, 1, 1, 2, 1), ar(1, 1, 1, 1, 1, 2, 2));
        Assert.assertEquals(asc(1, 1, 1, 1, 1, 2, 1), ar(1, 1, 1, 1, 1, 1, 2));
        Assert.assertEquals(asc(3, 2, 1), ar(1, 2, 3));
        Assert.assertEquals(asc(1, 2, 3), ar(1, 2, 3));
        Assert.assertEquals(asc(2, 0, -1, 2, 1), ar(-1, 0, 1, 2, 2));

        Assert.assertEquals(dsc(1, 2, 3), ar(3, 2, 1));
        Assert.assertEquals(dsc(3, 2, 1), ar(3, 2, 1));
        Assert.assertEquals(dsc(2, 0, -1, 2, 1), ar(2, 2, 1, 0, -1));

        Assert.assertEquals(asc(2, 1), ar(1, 2));
        Assert.assertEquals(asc(1), ar(1));
        Assert.assertEquals(asc(), ar());
        Assert.assertEquals(dsc(1), ar(1));
        Assert.assertEquals(dsc(), ar());
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testNullExcept() {
        Sorting.mergeSort(null, Integer::compare, true);
    }

    private static Integer[] ar(Integer... objects) {
        return objects;
    }

    private static Integer[] asc(Integer... objects) {
        return Sorting.mergeSort(objects, Integer::compare, true);
    }

    private static Integer[] dsc(Integer... objects) {
        return Sorting.mergeSort(objects, Integer::compare, false);
    }
}
