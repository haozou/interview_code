package com.hao.interview.dataStructure;

/**
 * Created by hzou on 7/13/17.
 */
public class GraphBuilder {
    public static Graph2<String, Double> construct(String[] array) {
        Graph2<String, Double> g = new Graph2();
        for (String str: array) {
            String[] tmp = str.split(",");
            g.addEdge(tmp[0], tmp[1], Double.parseDouble(tmp[2]));
        }
        return g;
    }
}
