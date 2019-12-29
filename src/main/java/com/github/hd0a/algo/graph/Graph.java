package com.github.hd0a.algo.graph;

public  interface Graph {
    void addEdge(int v, int w);
    void bfs(Visitor v, int start);
    void dfs(Visitor v, int start);

    @FunctionalInterface
    interface Visitor {
        void visit(int v);
    }
}
