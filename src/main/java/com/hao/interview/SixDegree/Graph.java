package com.hao.interview.SixDegree;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by hzou on 11/30/17.
 */
public class Graph<V, W> {
    static class Edge<V, W> {
        V from;
        V to;
        W weight;

        public Edge(V from, V to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + from + "," + to + "," + weight + ")";
        }
    }
    private Map<V, List<Graph.Edge<V, W>>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(final V from, final V to,final W weight) {
        if (adjList.containsKey(from)) {
            adjList.get(from).add(new Graph.Edge(from, to, weight));
        } else {
            adjList.put(from, new ArrayList<Graph.Edge<V, W>>() {
                {add(new Graph.Edge<>(from, to, weight));}
            });
        }
    }

    public boolean exists(V from) {
        return adjList.containsKey(from);
    }

    public void findPaths(V from, V to, List<V> curResult, List<List<V>> result) {
        if (!adjList.containsKey(from)) return;
        for (Edge<V, W> edge: adjList.get(from)) {
            List<V> newCurResult = new ArrayList<>(curResult);
            newCurResult.add(edge.to);
            if (edge.to.equals(to)) {
                if (result.isEmpty())
                    result.add(newCurResult);
                else if (result.get(0).size() > newCurResult.size())
                    result.set(0, newCurResult);
            } else {
                findPaths(edge.to, to, newCurResult, result);
            }
        }
    }

    /**
     * bfs find the shortest path
     * @param from
     * @param to
     * @return
     */
    public List<V> findShortest(V from, V to) {
        List<V> curResult = new ArrayList<>();
        if (from.equals(to)) {
            curResult.add(to);
            return curResult;
        }

        Queue<Pair<V, List<V>>> queue = new LinkedList<>();
        queue.add(new Pair<>(from, curResult));
        Set<V> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair<V, List<V>> node = queue.poll();
            if (!adjList.containsKey(node.getKey())
                    || visited.contains(node.getKey())) continue;

            visited.add(node.getKey());
            for (Edge<V, W> edge: adjList.get(node.getKey())) {
                List<V> newCurResult  = new ArrayList<>(node.getValue());
                newCurResult.add(edge.to);
                if (edge.to.equals(to)) {
                    return newCurResult;
                } else {
                    queue.add(new Pair<>(edge.to, newCurResult));
                }
            }
        }
        return null;

    }

//    public List<V> findShortest(V from, V to) {
//
//    }
}
