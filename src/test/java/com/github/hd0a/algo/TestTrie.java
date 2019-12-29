package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TestTrie {
    @Test
    public void testTrieAddContains() {
        var t = new Trie();
        Assert.assertEquals(t.size(), 0);
        t.add("ban");
        t.add("banana");
        Assert.assertEquals(t.size(), 2);
        t.add("cat");
        t.add("catch");
        Assert.assertEquals(t.size(), 4);
        Assert.assertTrue(t.containsAll(List.of("ban", "banana", "cat", "catch")));
        t.remove("cat");
        t.remove("banana");
        Assert.assertEquals(t.size(), 2);
        Assert.assertFalse(t.contains("cat"));
        Assert.assertFalse(t.contains("banana"));
        t.clear();
        Assert.assertEquals(t.size(), 0);
    }

    @Test
    public void testToArray() {
        var t = new Trie();
        var items = List.of("cat", "rat", "catch", "alt", "altogether", "nat", "nathan", "fat", "flat", "test");
        t.addAll(items);
        Assert.assertEqualsNoOrder(t.toArray(), items.toArray());

    }
}
