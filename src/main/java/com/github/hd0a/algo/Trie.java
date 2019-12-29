package com.github.hd0a.algo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Day 06 - Trie
 */
public class Trie implements Set<String> {

    private int size;
    private Node root;
    public Trie() {
        size = 0;
        root = new Node();
        root.children = new LinkedHashMap<>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object word) {
        Node node = root;
        var chars = word.toString().toCharArray();
        Node child;
        for (char currentChar : chars) {
            if (node.children == null) {
                return false;
            }
            child = node.children.get(currentChar);
            if (child == null) {
                return false;
            }
            node = child;
        }
        return node.word;
    }

    @Override
    public Iterator<String> iterator() {
        List<String> words = new LinkedList<>();
        String prefix = "";
        this.root.collectWords(prefix, words);
        return words.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.stream().collect(Collectors.toList()).toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String word) {
        Node node = root;
        Node child;
        var characters = word.toCharArray();
        for (char currentChar : characters) {
            if (node.children == null) {
                node.children = new LinkedHashMap<>();
            }
            child = node.children.get(currentChar);
            if (child == null) {
                child = new Node();
                node.children.put(currentChar, child);
            }
            node = child;
        }
        if (node.word) {
            return false;
        }
        node.word = true;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object word) {
        Node node = root;
        var chars = word.toString().toCharArray();
        Node child;
        for (char currentChar : chars) {
            if (node.children == null) {
                return false;
            }
            child = node.children.get(currentChar);
            if (child == null) {
                return false;
            }
            node = child;
        }
        if (node.word) {
            node.word = false;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return collection.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends String> collection) {
        return collection.stream().allMatch(this::add);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        root.word = false;
        size = 0;
        root.children = null;
    }

    private static class Node {
        private boolean word;
        private LinkedHashMap<Character, Node> children;

        public Node() {
            this.word = false;
            this.children = null;
        }

        private void collectWords(String prefix, List<String> col) {
            if (this.children == null) {
                return;
            }
            this.children.forEach((c, node) -> {
                var curLevel = prefix + c;
                if (node.word) {
                    col.add(curLevel);
                }
                node.collectWords(curLevel, col);
            });
        }
    }
}
