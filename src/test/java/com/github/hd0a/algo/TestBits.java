package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBits {

    @Test
    public void testSetBit() {
        int x = Integer.parseInt("100000", 2);
        int y = Bits.setBit(x, 1);
        Assert.assertEquals(Integer.toBinaryString(y), "100010");
        Assert.assertEquals(Integer.toBinaryString(Bits.unsetBit(y, 1)), "100000");
    }

    @Test
    public void testGetBit() {
        int x = Integer.parseInt("100000", 2);
        Assert.assertEquals(0, Bits.getBit(Bits.setBit(x, 4), 3));
        Assert.assertEquals(1, Bits.getBit(Bits.setBit(x, 4), 4));
    }

    @Test
    public void testUpdateBit() {
        int x = Integer.parseInt("100000", 2);
        x = Bits.updateBit(x, 1, true);
        x = Bits.updateBit(x, 2, true);
        x = Bits.updateBit(x, 3, true);
        x = Bits.updateBit(x, 5, false);
        Assert.assertEquals("1110", Integer.toBinaryString(x));
    }

    @Test
    public void testUpdateBit2() {
        int x = Integer.parseInt("100000", 2);
        x = Bits.updateBit2(x, 1, true);
        x = Bits.updateBit2(x, 2, true);
        x = Bits.updateBit2(x, 3, true);
        x = Bits.updateBit2(x, 5, false);
        Assert.assertEquals("1110", Integer.toBinaryString(x));
    }
}
