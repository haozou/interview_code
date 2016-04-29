package interview;

import java.util.*;

/**
 * Created by Hao on 4/13/16.
 */
public class Graph {

    int V;
    LinkedList<int[]>[] adjLists;
    public Graph (int V) {
        this.V = V;
        this.adjLists = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            this.adjLists[i] = new LinkedList<int[]>();
        }
    }
    public void addEdge(int src, int dst, int dist) {

        this.adjLists[src].add(new int[]{dst, dist});
    }

    public Integer[] shortestPath(int source) {
        Integer[] dist = new Integer[this.V];
        Integer[] prev = new Integer[this.V];
        boolean[] visited = new boolean[this.V];
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                }
                return 0;
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(V, comparator);

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[source] = 0;
        queue.add(new int[]{source, 0});
        while (!queue.isEmpty()) {
            int[] u = queue.remove();
            visited[u[0]] = true;
            for (int[] vertex: this.adjLists[u[0]]) {
                if (!visited[vertex[0]]) {
                    int tmp = vertex[1] + dist[u[0]];
                    if (tmp < dist[vertex[0]]) {
                        dist[vertex[0]] = tmp;
                        prev[vertex[0]] = u[0];
                        queue.add(new int[]{vertex[0], dist[vertex[0]]});
                    }
                }
            }
        }
        return dist;
    }

    public void dfs(int source) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node]) continue;
            System.out.println(node);
            visited[node] = true;
            for (int[] vertex: this.adjLists[node]) {
                stack.push(vertex[0]);
            }
        }
    }

    public void bfs(int source) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited[node]) continue;
            System.out.println(node);
            visited[node] = true;
            for (int[] vertex: this.adjLists[node]) {
                queue.add(vertex[0]);
            }
        }
    }
    private void dfs(int source, boolean[] visited) {
        if (visited[source]) return;
        System.out.println(source);
        visited[source] = true;
        for (int[] vertex: this.adjLists[source]) {
            dfs(vertex[0], visited);
        }
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < V; i++) {
            String level = String.valueOf(i) + ":";
            for (int[] node: this.adjLists[i]) {
                level += "[" + node[0] + "," + node[1] + "] ";
            }
            result += level + "\n";
        }
        return result;
    }
}
