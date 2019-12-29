package com.github.hd0a.algo;

import java.util.Comparator;
import java.util.Objects;

public class Searching {

    /**
     * Day 09 - Binary Search
     *
     * @param data       sorted data array to search
     * @param comparator comparator that can compare data
     * @param toFind     element to find
     * @param <T>        data type
     * @return index of given toFind item, returns -1 if it cannot be found
     */
    public static <T> int binarySearch(T[] data, Comparator<T> comparator, T toFind) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(comparator);
        int start = 0;
        int stop = data.length - 1;

        while (start <= stop && start < data.length && stop >= 0) {
            int middle = start + ((stop - start) / 2);
            int comparision = comparator.compare(data[middle], toFind);

            if (comparision == 0) {
                return middle;
            } else if (comparision > 0) {
                stop = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return -1;
    }
}
