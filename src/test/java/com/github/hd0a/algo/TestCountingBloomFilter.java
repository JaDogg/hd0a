package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TestCountingBloomFilter {

    @Test
    public void testCreateObj() {
        new CountingBloomFilter<String>(100, 10);
    }

    @Test
    public void testAddAndCheck() {
        Set<String> b = new CountingBloomFilter<>(40, 5);
        Assert.assertTrue(b.add("Hello World"));
        Assert.assertTrue(b.add("Yellow World"));
        Assert.assertTrue(b.contains("Hello World"));
        Assert.assertFalse(b.contains("Yello World"));
        Assert.assertTrue(b.contains("Yellow World"));
        Assert.assertTrue(b.add("Yello World"));
        Assert.assertTrue(b.contains("Yello World"));
        Assert.assertTrue(b.remove("Yello World"));
        Assert.assertFalse(b.contains("Yello World"));
    }

    @Test
    public void testAddAll() {
        Set<String> b = new CountingBloomFilter<>(1000, 4);
        Set<String> items = Set.of("Hello", "World", "Haha", "How are you!");
        Assert.assertTrue(b.addAll(items));
        Assert.assertTrue(b.contains("Haha"));
        Assert.assertTrue(b.containsAll(items));
        Assert.assertEquals(b.size(), items.size());
    }

    @Test
    public void testRetainAll() {
        Set<String> b = new CountingBloomFilter<>(100, 4);
        Set<String> items = Set.of("Hello", "World", "Haha", "How are you!");
        Assert.assertTrue(b.retainAll(items));
        Assert.assertTrue(b.contains("Haha"));
        Assert.assertTrue(b.containsAll(items));
        Assert.assertEquals(b.size(), items.size());
    }
}
