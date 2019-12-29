package com.github.hd0a.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Sorting {
    /**
     * Day 07 - Bubble Sort
     * In place sort given array with bubble sort algorithm
     *
     * @param data       array
     * @param comparator implementation of comparator for given data type
     * @param ascending  sort order
     * @param <T>        data type of array elements
     * @return sorted data array (in place)
     */
    public static <T> T[] bubbleSort(T[] data, Comparator<T> comparator, boolean ascending) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(comparator);
        if (data.length <= 1) return data;
        Comparator<T> actualComparator = ascending ? comparator : comparator.reversed();
        for (int i = data.length - 1; i >= 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (actualComparator.compare(data[j], data[j + 1]) > 0) {
                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return data;
    }

    /**
     * Day 08 - Merge Sort
     *
     * @param data       data array
     * @param comparator comparator of given data type
     * @param ascending  sort order
     * @param <T>        data type
     * @return sorted array (in place)
     */
    public static <T> T[] mergeSort(T[] data, Comparator<T> comparator, boolean ascending) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(comparator);
        if (data.length <= 1) return data;
        var actualComparator = ascending ? comparator : comparator.reversed();
        return mergeSort(data, actualComparator, 0, data.length - 1);
    }

    private static <T> T[] mergeSort(T[] data, Comparator<T> comparator, int start, int stop) {
        if (start >= stop) return data;
        int pivot = start + (stop - start) / 2;
        mergeSort(data, comparator, start, pivot);
        mergeSort(data, comparator, pivot + 1, stop);
        return merge(data, comparator, start, pivot, stop);
    }

    private static <T> T[] merge(T[] data, Comparator<T> comparator, int start, int pivot, int stop) {
        T[] left = Arrays.copyOfRange(data, start, pivot + 1);
        T[] right = Arrays.copyOfRange(data, pivot + 1, stop + 1);
        int lPos = 0;
        int rPos = 0;
        int pos = start;
        while (lPos < left.length && rPos < right.length) {
            if (comparator.compare(left[lPos], right[rPos]) <= 0) {
                data[pos++] = left[lPos++];
            } else {
                data[pos++] = right[rPos++];
            }
        }
        while (lPos < left.length) {
            data[pos++] = left[lPos++];
        }
        while (rPos < right.length) {
            data[pos++] = right[rPos++];
        }
        return data;
    }

    /**
     * Day 13 - insertion sort implementation
     *
     * @param data       data to be sorted
     * @param comparator comparator of data
     * @param ascending  is ascending
     * @param <T>        data type
     * @return sorted data (in place)
     */
    public static <T> T[] insertionSort(T[] data, Comparator<T> comparator, boolean ascending) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(comparator);
        if (data.length <= 1) return data;
        var actualComparator = ascending ? comparator : comparator.reversed();
        for (int i = 1; i < data.length; i++) {
            T currentElement = data[i];
            int j = i - 1;
            while (j >= 0 && actualComparator.compare(data[j], currentElement) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = currentElement;
        }
        return data;
    }

    /**
     * Day 15 - Counting sort for integers with limited set of possible values
     *
     * @param data            data to be sorted
     * @param rangeStart      value range start
     * @param rangeEndExclude value range end
     * @return sorted
     */
    public static int[] countingSort(int[] data, int rangeStart, int rangeEndExclude) {
        Objects.requireNonNull(data);
        int rangeSize = Math.abs(rangeEndExclude - rangeStart);
        if (rangeSize <= 1) return data;
        int[] count = new int[rangeSize];
        for (int i : data) {
            count[i - rangeStart]++;
        }
        int pos = 0;
        int countPos = 0;
        while (countPos < rangeSize) {
            if (count[countPos] <= 0) {
                countPos++;
                continue;
            }
            data[pos++] = rangeStart + countPos;
            count[countPos]--;
        }
        return data;
    }
}
