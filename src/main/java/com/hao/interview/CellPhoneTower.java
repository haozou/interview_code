package com.hao.interview;

import java.util.*;

/**
 * Created by hzou on 1/19/18.
 */
public class CellPhoneTower {
    static class Tower {
        int x;
        int y;
        int radius;
        public Tower(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    public static void main(String[] args) {
        System.out.println(16 << 4);
    }



    public static List<Tower> findTower(List<Tower> towers, int[] customerLocation) {
        List<Tower> towersWithProblem = new ArrayList<>();
        for (Tower tower: towers) {
            double xRange = Math.pow(tower.x - customerLocation[0], 2);
            double yRange = Math.pow(tower.y - customerLocation[1], 2);
            if (Math.sqrt(xRange + yRange) < tower.radius) {
                towersWithProblem.add(tower);
            }
        }
        return  towersWithProblem;

    }

    public static Map<int[], List<Tower>> initialize(List<Tower> towers) {
        Map<int[], List<Tower>> map = new HashMap<>();
        for (final Tower tower: towers) {
            List<int[]> result = new ArrayList<>();
            dfs(tower, tower.x, tower.y, new HashSet<int[]>(), result);
            for (int[] oneResult: result) {
                if (map.containsKey(oneResult)) {
                    map.get(oneResult).add(tower);
                } else {
                    map.put(oneResult, new ArrayList<Tower>(){{
                        add(tower);
                    }});
                }
            }
        }
        return map;
    }



    public static int[] getClosest(List<Tower> towers, int[] customerLocation) {
        return bfs(initialize(towers), customerLocation);

    }

    public static void dfs(Tower tower, int x, int y, Set<int[]> visited, List<int[]> result) {
        if (visited.contains(new int[]{x, y})) return;
        visited.add(new int[]{x, y});
        double xRange = Math.pow(tower.x - x, 2);
        double yRange = Math.pow(tower.y - y, 2);
        if (Math.sqrt(xRange + yRange) > tower.radius) return;
        result.add(new int[]{x, y});
        dfs(tower, x + 1, y, visited, result);
        dfs(tower, x - 1, y, visited, result);
        dfs(tower, x, y + 1, visited, result);
        dfs(tower, x, y - 1, visited, result);
    }

    public static int[] bfs(Map<int[], List<Tower>> map, int[] customerLocation) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{customerLocation[0], customerLocation[1]});
        Set<int[]> visited = new HashSet<int[]>();
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            if (visited.contains(location)) continue;
            visited.add(location);
            if (map.containsKey(location)) return location;
            queue.add(new int[]{location[0] + 1, location[1]});
            queue.add(new int[]{location[0] - 1, location[1]});
            queue.add(new int[]{location[0], location[1] + 1});
            queue.add(new int[]{location[0], location[1] - 1});
        }
        return null;

    }




}
