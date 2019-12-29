package com.github.hd0a.algo;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Day 02 - Counting Bloom Filter
 * We are using whatever hash you use (usual 32 bit hash) + hash mix algorithm
 */
public class CountingBloomFilter<T> extends AbstractSet<T> {
    private static final Random RANDOM = new Random();

    private final int cellCount;
    private final int hashCount;

    private int elementCount;
    private final int[] cells;
    private final long[] keys;
    private final int[] tempData;

    /**
     * @param size      size of the bloom filter in bytes
     * @param hashCount number of hashes to use
     */
    public CountingBloomFilter(int size, int hashCount) {
        this.cellCount = size;
        this.hashCount = hashCount;
        this.cells = new int[size];
        this.keys = new long[hashCount];
        this.tempData = new int[hashCount];
        IntStream.range(0, hashCount).forEach(i -> keys[i] = RANDOM.nextLong());
    }

    @Override
    public boolean contains(Object element) {
        Objects.requireNonNull(element);
        var hash = element.hashCode();
        for (long key : keys) {
            var hashMix = HashMix.hashMix(key, hash);
            int index = (int) Long.remainderUnsigned(hashMix, cellCount);
            if (0 == cells[index]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(T element) {
        Objects.requireNonNull(element);
        var hash = element.hashCode();
        for (int i = 0; i < hashCount; i++) {
            tempData[i] = (int) Long.remainderUnsigned(HashMix.hashMix(keys[i], hash), cellCount);
        }
        for (int idx : tempData) {
            if (cells[idx] == Integer.MAX_VALUE) {
                return false;
            }
        }
        for (int idx : tempData) {
            cells[idx]++;
        }
        elementCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        Objects.requireNonNull(element);
        var hash = element.hashCode();
        for (int i = 0; i < hashCount; i++) {
            tempData[i] = (int) Long.remainderUnsigned(HashMix.hashMix(keys[i], hash), cellCount);
        }
        for (int idx : tempData) {
            if (cells[idx] == 0) {
                return false;
            }
        }
        for (int idx : tempData) {
            cells[idx]--;
        }
        elementCount--;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }


    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        clear();
        boolean modified = false;

        for (Object item : collection) {
            modified |= add((T) item);
        }

        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        boolean modified = false;

        for (Object item : collection) {
            modified |= remove(item);
        }

        return modified;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Objects.requireNonNull(collection);
        boolean modified = false;

        for (T item : collection) {
            modified |= add(item);
        }

        return modified;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public void clear() {
        Arrays.fill(cells, 0);
        IntStream.range(0, hashCount).forEach(i -> keys[i] = RANDOM.nextLong());
        elementCount = 0;
    }

    @Override
    public String toString() {
        return "CountingBloomFilter{" +
                "cellCount=" + cellCount +
                ", hashCount=" + hashCount +
                ", elementCount=" + elementCount +
                ", cells=" + Arrays.toString(cells) +
                ", keys=" + Arrays.toString(keys) +
                '}';
    }
}
