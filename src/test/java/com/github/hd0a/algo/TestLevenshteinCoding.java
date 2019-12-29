package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLevenshteinCoding {

    @Test
    public void testEncode() {
        var coding = new LevenshteinCoding();
        Assert.assertEquals(coding.encode(0), "0");
        Assert.assertEquals(coding.encode(1), "10");
        Assert.assertEquals(coding.encode(2), "1100");
        Assert.assertEquals(coding.encode(3), "1101");
        Assert.assertEquals(coding.encode(4),"1110000");
        Assert.assertEquals(coding.encode(5),"1110001");
        Assert.assertEquals(coding.encode(6),"1110010");
        Assert.assertEquals(coding.encode(7),"1110011");
        Assert.assertEquals(coding.encode(8),"11101000");
        Assert.assertEquals(coding.encode(9),"11101001");
        Assert.assertEquals(coding.encode(10),"11101010");
        Assert.assertEquals(coding.encode(11),"11101011");
        Assert.assertEquals(coding.encode(12),"11101100");
        Assert.assertEquals(coding.encode(13),"11101101");
        Assert.assertEquals(coding.encode(14),"11101110");
        Assert.assertEquals(coding.encode(15),"11101111");
        Assert.assertEquals(coding.encode(16), "111100000000");
        Assert.assertEquals(coding.encode(17), "111100000001");
    }

    @Test
    public void testDecode() {
        var coding = new LevenshteinCoding();
        Assert.assertEquals(coding.decode("111100000001"), 17);
        for (int i = 0; i <= 1000; i++) {
            Assert.assertEquals(coding.decode(coding.encode(i)), i);
        }
    }
}
