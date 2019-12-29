package com.github.hd0a.algo;

import java.util.*;

/**
 * Day 04, Day 05
 * Link list that stores set of unrolled elements
 */
public class UnrolledLinkedList<E> extends AbstractList<E> {
    private static class Node<E> {
        private E[] elements;
        private Node<E> next = null;

        @SuppressWarnings("unchecked")
        Node(int bucketSize) {
            this.elements = (E[]) new Object[bucketSize];
        }

        @Override
        public String toString() {
            return Arrays.toString(elements) + "->" + next;
        }
    }

    private final Node<E> head;
    private final int bucketSize;
    private Node<E> tail;
    private int lastBucketIndex;
    private int size;

    public UnrolledLinkedList(int bucketSize) {
        this.bucketSize = bucketSize;
        head = new Node<>(bucketSize);
        tail = head;
        lastBucketIndex = -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object item) {
        var node = head;
        while (node != null) {
            for (E element : node.elements) {
                if (element == null) {
                    return false;
                } else if (element == item) {
                    return true;
                }
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if (lastBucketIndex + 1 < bucketSize) {
            tail.elements[++lastBucketIndex] = e;
            size++;
            return true;
        }

        lastBucketIndex = 0;
        tail.next = new Node<>(bucketSize);
        tail = tail.next;
        tail.elements[0] = e;

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx = this.indexOf(o);
        if (idx < 0) return false;
        this.remove(idx);
        return true;
    }

    @Override
    public E remove(int idx) {
        if (idx >= this.size) {
            throw new IndexOutOfBoundsException(idx);
        }
        int bucket = idx / bucketSize;
        int cell = idx % bucketSize;
        int remainder = bucketSize - cell - 1;
        var node = head;
        var prev = node;
        while (bucket-- > 0) {
            prev = node;
            node = node.next;
        }
        E elem = node.elements[cell];
        if (remainder > 0) {
            System.arraycopy(node.elements, cell + 1, node.elements, cell, remainder);
        }
        while (node.next != null) {
            node.elements[bucketSize - 1] = node.next.elements[0];
            System.arraycopy(node.next.elements, 1, node.next.elements, 0, bucketSize - 1);
            prev = node;
            node = node.next;
        }
        // we no longer need the last bucket
        if (lastBucketIndex == 0) {
            if (prev.next != null)
            prev.next.elements = null; // we shouldn't access this at all so null it
            prev.next = null;
            lastBucketIndex = bucketSize - 1;
            tail = prev;
        } else {
            Arrays.fill(tail.elements, lastBucketIndex , bucketSize, null);
            lastBucketIndex--;
        }
        size--;
        if (size == 0) {
            lastBucketIndex = -1;
        }
        return elem;
    }

    @Override
    public E get(int idx) {
        if (idx >= this.size) {
            throw new IndexOutOfBoundsException(idx);
        }
        int bucket = idx / bucketSize;
        int cell = idx % bucketSize;
        var node = head;
        while (bucket-- > 0) {
            node = node.next;
        }
        return node.elements[cell];
    }

    @Override
    public void clear() {
        lastBucketIndex = -1;
        head.next = null;
        tail = head;
        size = 0;
    }

    @Override
    public String toString() {
        return head.toString();
    }
}
