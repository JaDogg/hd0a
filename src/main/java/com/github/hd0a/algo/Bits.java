package com.github.hd0a.algo;

public final class Bits {
    public static int setBit(int num, int bit) {
        return num | 1 << bit;
    }

    public static int getBit(int num, int bit) {
        return (num & (1 << bit)) == 0 ? 0 : 1;
    }

    public static int unsetBit(int num, int bit) {
        // (num | ~num) = -1 => all bits set
        // all bits ^ 1 << bit => all bits are set except bit at the location `bit`
        // Using & will unset the respective bit now
        // return num & ((num | ~num) ^ 1 << bit) ;
        // return num & ((-1) ^ 1 << bit);
        // we can simply use ~ to do above ((-1) ^ 1 << bit) as well.
        // Below is the simplified form::
        return num & (~(1 << bit)) ;
    }

    public static int updateBit(int num, int bit, boolean setIt) {
        return setIt ? setBit(num, bit) : unsetBit(num, bit);
    }

    // We are inlining set/unset here with mask!
    public static int updateBit2(int num, int bit, boolean setIt) {
        int value = setIt ? 1 : 0;
        int mask = ~(1 << bit);
        return (num & mask) | (value << bit);
    }
}
