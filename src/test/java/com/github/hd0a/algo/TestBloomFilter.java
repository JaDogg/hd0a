package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestBloomFilter {

    @Test
    public void testCreateObj() {
        new BloomFilter(100, 10);
    }

    @Test
    public void testAddAndCheck() {
        BloomFilter b = new BloomFilter(4, 5);
        b.insert("Hello World");
        b.insert("Yellow World");
        Assert.assertTrue(b.maybeExists("Hello World"));
        Assert.assertFalse(b.maybeExists("Yello World"));
        Assert.assertTrue(b.maybeExists("Yellow World"));
        b.insert("Yello World");
        Assert.assertTrue(b.maybeExists("Yello World"));
    }
}
