package com.github.hd0a.algo;

/**
 * HashMix algorithm allows you to use a single hash as multiple hashes.
 * This is based on the code in Google Chrome
 */
public final class HashMix {
    private HashMix() {
        throw new UnsupportedOperationException();
    }

    public static long hashMix(long hashKey, long hash) {
        long a = hashKey & 0xFFFFFFFFL;
        long b = (hashKey >> 32L) & 0xFFFFFFFFL;
        long c = hash;

        a -= (b + c);  a ^= (c >> 13L);
        b -= (c + a);  b ^= (a << 8L);
        c -= (a + b);  c ^= (b >> 13L);
        a -= (b + c);  a ^= (c >> 12L);
        b -= (c + a);  b ^= (a << 16L);
        c -= (a + b);  c ^= (b >> 5L);
        a -= (b + c);  a ^= (c >> 3L);
        b -= (c + a);  b ^= (a << 10L);
        c -= (a + b);  c ^= (b >> 15L);

        return c;
    }
}
