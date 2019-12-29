package com.github.hd0a.algo;

import java.util.*;
import java.lang.*;

/**
 * Day 16 - Count total bits set
 * https://practice.geeksforgeeks.org/problems/count-total-set-bits
 */
class GfgCountTotalBitsSet {
    private static final Map<Integer, Integer> CACHE = new HashMap<>();
    
    public static int calc(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        Integer val = CACHE.get(n);
        if (val != null) return val;
        val =  calc(n - 1) + bits(n);
        CACHE.put(n, val);
        return val;
    }
    
    public static int bits(int n) {
        int b = 0;
        while (n > 0) {
            b += n & 1;
            n >>= 1;
        }
        return b;
    }
    
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            System.out.println(calc(in.nextInt()));
        }
	}
}