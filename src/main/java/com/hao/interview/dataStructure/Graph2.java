package com.hao.interview.dataStructure;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by hzou on 7/13/17.
 */
public class Graph2<V, W> {
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

    protected Map<V, List<Edge<V, W>>> adjList;

    public Graph2() {
        this.adjList = new HashMap<>();
    }

    public void addVertex(final V from) {
        adjList.put(from, new ArrayList<Edge<V, W>>());
    }

    public void addEdge(final V from, final V to, final W weight) {
        if (!adjList.containsKey(from)) {
            adjList.put(from, new ArrayList<Edge<V, W>>());
        }
        adjList.get(from).add(new Edge<>(from, to, weight));
        if (!adjList.containsKey(to)) {
            adjList.put(to, new ArrayList<Edge<V, W>>());
        }

    }

    public void dfs() {
        Set<V> visited = new HashSet<>();
        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;
            Stack<V> stack = new Stack<>();
            stack.add(entry.getKey());
            while (!stack.isEmpty()) {
                V vertex = stack.pop();
                if (visited.contains(vertex))
                    continue;
                System.out.print(vertex + " ");
                visited.add(vertex);
                if (!adjList.containsKey(vertex))
                    continue;
                for (Edge<V, W> edge : adjList.get(vertex)) {
                    stack.push(edge.to);
                }
            }
        }
        System.out.println();
    }

    public void bfs() {
        Set<V> visited = new HashSet<>();
        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;
            Queue<V> queue = new LinkedList<>();
            queue.add(entry.getKey());
            while (!queue.isEmpty()) {
                V vertex = queue.poll();
                if (visited.contains(vertex))
                    continue;
                System.out.print(vertex + " ");
                visited.add(vertex);
                if (!adjList.containsKey(vertex))
                    continue;
                for (Edge<V, W> edge : adjList.get(vertex)) {
                    queue.add(edge.to);
                }
            }
        }
        System.out.println();
    }

    public void findPaths(V from, V to, String curResult, List<String> result) {
        if (!adjList.containsKey(from))
            return;
        for (Edge<V, W> edge : adjList.get(from)) {
            String newCurResult = curResult + " " + edge;
            if (edge.to.equals(to)) {
                result.add(newCurResult);
            } else {
                findPaths(edge.to, to, newCurResult, result);
            }
        }
    }

    public List<String> findPaths(V from, V to) {
        List<String> result = new ArrayList<>();
        result.clear();
        String curResult = "";
        Stack<Pair<V, String>> stack = new Stack<>();
        stack.push(new Pair<V, String>(from, curResult));
        while (!stack.isEmpty()) {
            Pair<V, String> node = stack.pop();
            if (!adjList.containsKey(node.getKey()))
                continue;

            for (Edge<V, W> edge : adjList.get(node.getKey())) {
                String newCurResult = node.getValue() + " " + edge;
                if (edge.to.equals(to)) {
                    result.add(newCurResult);
                } else {
                    stack.add(new Pair<V, String>(edge.to, newCurResult));
                }
            }
        }
        return result;
    }

    public void topologicalSort(V v, Set<V> visited, Stack<V> stack, Set<V> recStack) {

        if (recStack.contains(v))
            throw new RuntimeException("detect cycle");
        if (visited.contains(v)) {
            return;
        }

        visited.add(v);
        recStack.add(v);

        if (adjList.containsKey(v)) {
            for (Edge<V, W> edge : adjList.get(v)) {
                topologicalSort(edge.to, visited, stack, recStack);
            }
        }
        recStack.remove(v);
        stack.push(v);
    }

    public List<V> topologicalSort() {
        Set<V> visited = new HashSet<>();
        Set<V> recStack = new HashSet<>();
        Stack<V> stack = new Stack<>();
        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;
            topologicalSort(entry.getKey(), visited, stack, recStack);
        }
        List<V> result = new ArrayList<>();
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    public List<V> topologicalSort2() {
        List<V> result = new ArrayList<>();
        Queue<V> q = new LinkedList<>();
        Map<V, Integer> indegree = new HashMap<>();
        int count = 0;

        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (!indegree.containsKey(entry.getKey()))
                indegree.put(entry.getKey(), 0);
            for (Edge<V, W> edge : entry.getValue()) {
                if (indegree.containsKey(edge.to))
                    indegree.put(edge.to, indegree.get(edge.to) + 1);
                else
                    indegree.put(edge.to, 1);
            }
        }

        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (indegree.get(entry.getKey()) == 0)
                q.add(entry.getKey());
        }

        while (!q.isEmpty()) {
            V vertex = q.poll();
            result.add(vertex);

            if (adjList.containsKey(vertex)) {
                for (Edge<V, W> edge : adjList.get(vertex)) {
                    indegree.put(edge.to, indegree.get(edge.to) - 1);
                    if (indegree.get(edge.to) == 0)
                        q.add(edge.to);
                }
            }
            count++;
        }
        if (count != indegree.size())
            throw new RuntimeException("detect cycle");

        return result;
    }

    public List<List<V>> getAllTopologicalSort() {
        List<List<V>> result = new ArrayList<>();
        Map<V, Integer> indegree = new HashMap<>();
        Set<V> visited = new HashSet<>();
        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (!indegree.containsKey(entry.getKey()))
                indegree.put(entry.getKey(), 0);
            for (Edge<V, W> edge : entry.getValue()) {
                if (indegree.containsKey(edge.to))
                    indegree.put(edge.to, indegree.get(edge.to) + 1);
                else
                    indegree.put(edge.to, 1);
            }
        }

        getAllTopologicalSort(new ArrayList<V>(), result, visited, indegree);
        return result;
    }

    public void getAllTopologicalSort(List<V> oneResult, List<List<V>> result, Set<V> visited,
            Map<V, Integer> indegree) {
        boolean flag = false;
        for (Map.Entry<V, List<Edge<V, W>>> entry : adjList.entrySet()) {
            if (indegree.get(entry.getKey()) == 0 && !visited.contains(entry.getKey())) {
                for (Edge<V, W> edge : entry.getValue()) {
                    indegree.put(edge.to, indegree.get(edge.to) - 1);
                }
                oneResult.add(entry.getKey());
                visited.add(entry.getKey());
                getAllTopologicalSort(oneResult, result, visited, indegree);

                oneResult.remove(oneResult.size() - 1);
                visited.remove(entry.getKey());
                for (Edge<V, W> edge : entry.getValue()) {
                    indegree.put(edge.to, indegree.get(edge.to) + 1);
                }
                flag = true;
            }
        }
        if (!flag)
            result.add(new ArrayList<V>(oneResult));
    }
}
