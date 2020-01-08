package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryIndexedTreeTest {
    @Test
    public void testIncrease() {
        long[] original = {1, 2, 3, 4, 5};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(original);
        binaryIndexedTree.increase(0, 5);
        // now first element should be 6, therefore sum of element 0 .. 0 is 6.
        Assert.assertEquals(binaryIndexedTree.sumUpTo(0), 6);
        // sum of elements at indexes  0 .. 1 is 8
        Assert.assertEquals(binaryIndexedTree.sumUpTo(1), 8);
    }

    @Test
    public void testIncreaseSingleElement() {
        long[] original = {1};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(original);
        binaryIndexedTree.increase(0, 5);
        // now first element should be 6, therefore sum of element 0 .. 0 is 6.
        Assert.assertEquals(binaryIndexedTree.sumUpTo(0), 6);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testEmptyArraySum() {
        long[] original = {};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(original);
        Assert.assertEquals(binaryIndexedTree.sumUpTo(0), 0);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testEmptyArraySumWithSizeConstructor() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(0);
        Assert.assertEquals(binaryIndexedTree.sumUpTo(0), 0);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testEmptyArrayIncrease() {
        long[] original = {};
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(original);
        binaryIndexedTree.increase(0, 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNoNullArrayAllowed() {
        new BinaryIndexedTree(null);
    }

    @Test
    public void testSumUpTo() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(4);
        binaryIndexedTree.increase(0, 1);
        binaryIndexedTree.increase(1, 2);
        binaryIndexedTree.increase(2, 3);
        binaryIndexedTree.increase(3, 4);
        Assert.assertEquals(binaryIndexedTree.sumUpTo(3), 1 + 2 + 3 + 4);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(new BinaryIndexedTree(0).size(), 0);
        Assert.assertEquals(new BinaryIndexedTree(2).size(), 2);
        Assert.assertEquals(new BinaryIndexedTree(array(1, 2, 3)).size(), 3);
        Assert.assertEquals(new BinaryIndexedTree(array()).size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSizeNegativeResultsInProblem() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(-1);
    }

    @Test
    public void testRangeSum() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(4);
        binaryIndexedTree.increase(0, 1);
        binaryIndexedTree.increase(1, 2);
        binaryIndexedTree.increase(2, 3);
        binaryIndexedTree.increase(3, 4);
        Assert.assertEquals(binaryIndexedTree.rangeSum(1, 2), 2 + 3);
        Assert.assertEquals(binaryIndexedTree.rangeSum(0, 0), 1);
        Assert.assertEquals(binaryIndexedTree.rangeSum(1, 1), 2);
        Assert.assertEquals(binaryIndexedTree.rangeSum(2, 2), 3);
        Assert.assertEquals(binaryIndexedTree.rangeSum(3, 3), 4);
        Assert.assertEquals(binaryIndexedTree.rangeSum(1, 3), 2 + 3 + 4);
    }

    @Test
    public void testWithNegativeNumbers() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(array(0, 1, 2, -1, -2, 0, 0));
        Assert.assertEquals(binaryIndexedTree.sumUpTo(binaryIndexedTree.size() - 1), 0);
    }

    private static long[] array(long... elements) {
        return elements;
    }
}