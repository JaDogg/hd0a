package com.github.hd0a.algo;

import java.util.Objects;

/**
 * Day 17 - BinaryIndexedTree
 * BinaryIndexedTree allows faster ranges sums of O(log(n))
 * However increasing a single index with delta is also O(log(n))
 * Note: This doesn't check for overflow
 *
 * @author Bhathiya
 */
public class BinaryIndexedTree {
    private final long[] internalData;
    private final int size;

    /**
     * Construct from given array
     *
     * @param originalArray original array to process
     */
    public BinaryIndexedTree(long[] originalArray) {
        Objects.requireNonNull(originalArray);
        this.size = originalArray.length;
        internalData = new long[size + 1];
        for (int i = 0; i < size; i++) {
            increase(i, originalArray[i]);
        }
    }

    /**
     * Construct an empty BinaryIndexedTree
     *
     * @param size element count
     */
    public BinaryIndexedTree(int size) {
        if (size < 0) throw new IllegalArgumentException("Size cannot be less than zero");
        this.size = size;
        internalData = new long[size + 1];
    }


    /**
     * Increase given index with value delta
     * similar to array[index] += delta
     *
     * @param index index in array
     * @param delta value to add to specific index
     */
    public void increase(int index, long delta) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        index += 1;
        while (index <= this.size) {
            internalData[index] += delta;
            // WHY:
            // Faster way to get the last set bit of index using 2's complement
            //  then we add it to index to find all cells that needs to be incremented
            index += index & (-index);
        }
    }

    /**
     * Calculate sum of values in array[0] ... array[index] (inclusive)
     *
     * @param index index in array
     * @return sum
     */
    public long sumUpTo(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        int sum = 0;
        index += 1;
        while (index > 0) {
            sum += internalData[index];
            // WHY:
            // Faster way to get the last set bit of index using 2's complement
            //  then we subtract it from index to find all cells that contains values that
            //  we need to add to `sum` variable so we can find sumUpTo given original index
            index -= index & (-index);
        }
        return sum;
    }

    /**
     * Calculate sum of array[fromIndex] .. array[toIndex]
     *
     * @param fromIndex range from index in array
     * @param toIndex   range to index in array
     * @return sum of elements in specified index range
     */
    public long rangeSum(int fromIndex, int toIndex) {
        if (fromIndex == 0) {
            return sumUpTo(toIndex);
        }
        return sumUpTo(toIndex) - sumUpTo(fromIndex - 1);
    }

    /**
     * @return size of BinaryIndexedTree
     */
    public int size() {
        return this.size;
    }
}
