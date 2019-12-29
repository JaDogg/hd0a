package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUnrolledLinkedList {
    @Test
    public void testAddGetDisplay() {
        var u = new UnrolledLinkedList<Integer>(3);
        for (var i = 0; i < 10; i++) {
            u.add(i);
        }
        for (var i = 0; i < 10; i++) {
            Assert.assertEquals(u.get(i), Integer.valueOf(i));
        }
        Assert.assertEquals(u.toString(), "[0, 1, 2]->[3, 4, 5]->[6, 7, 8]->[9, null, null]->null");
    }

    @Test
    public void testRemove() {
        var u = new UnrolledLinkedList<Integer>(3);
        Assert.assertEquals(u.toString(), "[null, null, null]->null");
        for (var i = 0; i < 10; i++) {
            u.add(i);
        }
        u.remove(3);
        Assert.assertEquals(u.toString(), "[0, 1, 2]->[4, 5, 6]->[7, 8, 9]->null");
        Assert.assertEquals(u.get(3), Integer.valueOf(4));
        Assert.assertEquals(u.get(4), Integer.valueOf(5));
        Assert.assertEquals(u.get(5), Integer.valueOf(6));
        Assert.assertEquals(u.get(6), Integer.valueOf(7));
        Assert.assertEquals(u.get(7), Integer.valueOf(8));
        u.add(10);
        Assert.assertEquals(u.toString(), "[0, 1, 2]->[4, 5, 6]->[7, 8, 9]->[10, null, null]->null");

        // remove from smallest to largest
        u.clear();
        for (var i = 0; i < 10; i++) {
            u.add(i);
        }
        for (var i = 0; i < 10; i++) {
            // remove object
            Assert.assertTrue(u.remove(Integer.valueOf(i)));
        }
        Assert.assertEquals(u.toString(), "[null, null, null]->null");
        Assert.assertEquals(u.size(), 0);

        // remove from largest to smallest
        for (var i = 0; i < 10; i++) {
            u.add(i);
        }
        System.out.println(u);
        for (var i = 9; i >= 0; i--) {
            // remove object
            Assert.assertTrue(u.remove(Integer.valueOf(i)));
            Assert.assertEquals(u.size(), i);
        }
        Assert.assertEquals(u.toString(), "[null, null, null]->null");
        Assert.assertEquals(u.size(), 0);
    }
}
