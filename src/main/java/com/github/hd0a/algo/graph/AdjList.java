package com.github.hd0a.algo.graph;

import java.util.*;
import java.util.stream.IntStream;

/**
 * AdjacencyList implementation of a Graph
 * ------
 * Difference between DFS and BFS
 * BFS - queue, checked if visited before adding to queue
 * DFS - stack, checked after popping an item
 */
public class AdjList implements Graph {
    private final List<List<Integer>> vertices;
    private int numberOfVertices;

    /**
     * Create a graph
     *
     * @param numberOfVertices create vertices from 0 to numberOfVertices
     */
    public AdjList(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        vertices = new ArrayList<>();
        IntStream.range(0, numberOfVertices).forEach(ignored -> vertices.add(new ArrayList<>()));
    }

    @Override
    public void addEdge(int v, int w) {
        vertices.get(v).add(w);
    }

    /**
     * Day 11 - non recursive BFS
     * Visit from a given node in BFS pattern
     *
     * @param visitor visitor function/object
     * @param start   starting vertex
     */
    @Override
    public void bfs(Visitor visitor, int start) {
        final boolean[] visited = new boolean[numberOfVertices];
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            visitor.visit(current);
            var adjacent = vertices.get(current);
            for (Integer v : adjacent) {
                if (!visited[v]) {
                    queue.add(v);
                }
            }
        }
    }

    /**
     * Day 12 - non recursive DFS
     *
     * @param visitor visitor function/object
     * @param start   starting vertex
     */
    @Override
    public void dfs(Visitor visitor, int start) {
        final boolean[] visited = new boolean[numberOfVertices];
        final Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                visitor.visit(current);
                var adjacent = vertices.get(current);
                for (Integer v : adjacent) {
                    stack.push(v);
                }
            }
        }
    }
}
