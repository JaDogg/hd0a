package com.github.hd0a.algo;

import com.github.hd0a.algo.graph.AdjList;
import com.github.hd0a.algo.graph.Graph;
import org.testng.annotations.Test;

public class TestAdjList {
    @Test
    public void testBfsDfs() {
        Graph g = new AdjList(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("BFS:");
        g.bfs(i -> System.out.printf(" %d", i), 2);


        g = new AdjList(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        System.out.println();
        System.out.println("DFS:");
        g.dfs(i -> System.out.printf(" %d", i), 0);
    }
}
