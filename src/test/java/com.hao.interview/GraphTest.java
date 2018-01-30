package com.hao.interview;

import com.hao.interview.dataStructure.Graph2;
import com.hao.interview.dataStructure.GraphBuilder;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzou on 7/13/17.
 */
public class GraphTest {

    @Test
    public void testGraph() {
        Graph2<String, Double> g = GraphBuilder.construct(new String[]{
                "A,B,0.5",
                "B,C,0.6",
                "C,D,0.7",
                "A,E,0.8",
                "C,F,0.9",
                "E,D,0.2",
                "D,F,0.3",
                "F,C,0.5",
                "G,L,0.7",
        });
        System.out.println(g.findPaths("A", "F"));
        g.dfs();
        g.bfs();

        g = GraphBuilder.construct(new String[]{"5,2,1",
        "5,0,1",
        "2,3,1",
        "3,1,1",
        "4,1,1",
        "4,0,0"});
        System.out.println(g.topologicalSort2());
        System.out.println(g.getAllTopologicalSort());

        printAllOrders(new ArrayList<String>(){{
            add("abc");
            add("bcc");
            add("dcc");
            add("defrhy");
            add("gdd");
            add("gdd");
            add("gld");
        }});
    }

    public void printAllOrders(List<String> strings) {
        Graph2<Character, Integer> g = new Graph2<>();
        for (int i = 0; i < strings.size() - 1; i++) {
            for (Character c: strings.get(i).toCharArray()) g.addVertex(c);
        }

        for (int i = 0; i < strings.size() - 1; i++) {
            String word1 = strings.get(i), word2 = strings.get(i + 1);
            for (int j = 0;  j < word1.length() && j < word2.length(); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    g.addEdge(word1.charAt(j), word2.charAt(j), 1);
                    break;
                }
            }
        }
        System.out.println(g.getAllTopologicalSort());
    }
}
