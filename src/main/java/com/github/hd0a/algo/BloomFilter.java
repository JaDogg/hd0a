package com.github.hd0a.algo;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Day 01
 * A String-BloomFilter with JenkinsAtAOnceHash used with HashMix
 * @author Bhathiya Perera
 */
public class BloomFilter {
    private static final int BITS_IN_BYTE = 8;
    private static final Random RANDOM = new Random();

    private final byte[] data;
    private final long[] keys;
    private final int byteSize;
    private final int bitSize;

    /**
     * @param size size of the bloom filter in bytes
     * @param hashCount number of hashes to use
     */
    public BloomFilter(int size, int hashCount) {
        byteSize = size;
        bitSize = size * BITS_IN_BYTE;
        data = new byte[byteSize];
        /* We are using random 64 bit keys and using that with hash mix to generate multiple hashes */
        keys = new long[hashCount];
        IntStream.range(0, hashCount).forEach(i -> keys[i] = RANDOM.nextLong());
    }

    private static long jenkins(String item) {
        long hash = 0;
        byte[] bytes = item.getBytes(StandardCharsets.UTF_8);

        for (byte currentByte : bytes) {
            long currentChar = Byte.toUnsignedLong(currentByte);
            hash += currentChar;
            hash += hash << 10L;
            hash ^= hash >> 6L;
        }

        hash += hash << 3L;
        hash ^= hash >> 11L;
        hash += hash << 15L;

        return hash;
    }

    public void insert(String item) {
        long hash = jenkins(item); // this is the hash of the item
        for (long hashKey: keys) {
            // WHY? Instead of doing another hash we use hash mix to generate a new hash
            long currentHashMix = HashMix.hashMix(hashKey, hash);
            long index = Long.remainderUnsigned(currentHashMix, bitSize);
            data[(int) Long.divideUnsigned(index, BITS_IN_BYTE)] |= (1L << Long.remainderUnsigned(index, BITS_IN_BYTE));
        }
    }

    public boolean maybeExists(String item) {
        long hash = jenkins(item); // this is the hash of the item
        for (long key: keys) {
            long hashMix = HashMix.hashMix(key, hash);
            long index = Long.remainderUnsigned(hashMix, bitSize);
            if (0L == (data[(int) Long.divideUnsigned(index, BITS_IN_BYTE)] & (1L << Long.remainderUnsigned(index, BITS_IN_BYTE)))) {
                return false;
            }
        }
        return true;
    }
}
