package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestSearching {
    @Test
    public void testBinarySearch() {
        Assert.assertEquals(find(1, array()), -1);
        Assert.assertEquals(find(1, array(1)), 0);
        Assert.assertEquals(find(1, array(2)), -1);
        Assert.assertEquals(find(1, array(2, 2)), -1);
        Assert.assertEquals(find(1, array(2, 2, 2)), -1);
        Assert.assertEquals(find(1, array(2, 2, 2, 4, 5, -1, 2, 4)), -1);
        Assert.assertEquals(find(2, array(1, 2, 4)), 1);
        Assert.assertEquals(find(2, array(-2, 2, 2)), 1);
        Assert.assertEquals(find(5, array(1, 3, 5, 6, 9)), 2);
        Assert.assertEquals(find(3, array(1, 3, 5, 6, 9)), 1);
        Assert.assertEquals(find(1, array(1, 3, 5, 6, 9)), 0);
        Assert.assertEquals(find(9, array(1, 3, 5, 6, 9)), 4);
        Assert.assertEquals(find(5, array(1, 3, 4, 5, 6, 9)), 3);
        Assert.assertEquals(find(4, array(1, 3, 4, 5, 6, 9)), 2);
        Assert.assertEquals(find(9, array(1, 3, 4, 5, 6, 9)), 5);
        Assert.assertEquals(find(1, array(1, 3, 4, 5, 6, 9)), 0);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testNullExceptData() {
        Searching.binarySearch(null, Integer::compare, 1);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testNullExceptComparator() {
        Searching.binarySearch(array(1, 2, 3), null, 1);
    }

    private static Integer[] array(Integer... i) {
        return Sorting.mergeSort(i, Integer::compare, true);
    }

    private static int find(Integer target, Integer[] data) {
        return Searching.binarySearch(data, Integer::compare, target);
    }
}
