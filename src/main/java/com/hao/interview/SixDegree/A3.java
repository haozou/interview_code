package com.hao.interview.SixDegree;


import java.util.List;
import java.util.Scanner;

/**
 * Created by hzou on 11/30/17.
 */
public class A3 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: A3 <inputPath>");
            System.exit(1);
        }
        String inputPath = args[0];
        Graph<String, Integer> graph = RecordLoader.load(inputPath);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Actor 1 name: ");
            String actor1 = scanner.nextLine();
            if (!graph.exists(actor1)) {
                System.err.println("No such actor.");
                continue;
            }
            System.out.print("Actor 2 name: ");
            String actor2 = scanner.nextLine();
            if (!graph.exists(actor2)) {
                System.err.println("No such actor.");
                continue;
            }
            List<String> actors = graph.findShortest(actor1, actor2);
            if (actors == null) {
                System.err.println("no such path that less than 6 degree");
                continue;
            }
            String path = "";
            for (int i = 0; i < actors.size(); i++) {
                if (i == actors.size() - 1) {
                    path += actors.get(i);
                } else {
                    path += actors.get(i) + " --> ";
                }
            }
            System.out.println(String.format("Path between %s and %s: %s -> %s", actor1, actor2, actor1, path));

        }
    }
}
