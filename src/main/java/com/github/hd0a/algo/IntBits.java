package com.github.hd0a.algo;

public class IntBits {

    public static int lastSetBit(int number) {
        return number & -number;
    }

    public static int firstSetBit(int number) {
        int msb = 0;
        while (number != 0) {
            number >>= 1;
            msb++;
        }

        return (1 << msb);
    }

    public static int firstSetBit32(int number) {
        // set all bits lower than first set bit
        // then add one so we get to a level higher than first set bit
        // then we shift to get the correct one
        number |= number >> 1;
        number |= number >> 2;
        number |= number >> 4;
        number |= number >> 8;
        number |= number >> 16;
        number += 1;
        return number >> 1;
    }

    public static int setBit(int number, int pos) {
        return number | (1 << pos);
    }

    public static boolean isBitSet(int number, int pos) {
        return ((number >> pos - 1) & 1) == 1;
    }

}
