package com.hao.interview;

import java.util.*;

/**
 * Created by Hao on 2/19/16.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(getTableMemUsage());
        QuestionForStringAndArray question = new QuestionForStringAndArray();
        QuestionForLinkedList q2 = new QuestionForLinkedList();
        QuestionForOthers q3 = new QuestionForOthers();
        QuestionForTree q4 = new QuestionForTree();
        char[] chars = new char[]{'a','b','c', 'c', 'b', 'a'};
        question.reverseArray(chars);
        System.out.println(chars);

        question.removeDuplicates2(chars);
        System.out.println(chars);

        System.out.println(question.replaceFun(new char[]{'a', ' ', 'c', 'd', ' ', 'e'}));

        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}};
        question.rotateImage(matrix);
        printMatrix(matrix);

        QuestionForLinkedList.LinkedNode node = generateLinkedNode(10);
        printLinkedNode(q2.addLists(node, node.next));
        printLinkedNode(node);
        System.out.println(q2.findMiddle(node).val);

        System.out.println(q3.canWin(new int[]{1, 3, 2, 0, 5, 2, 8, 4, 1}, 5));
        printLinkedNode(q2.nthToLast(node, 5));

        QuestionForTree.TreeNode treeNode = q4.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8});
        q4.printLevelOrder(treeNode);
        q4.inOrder(treeNode);

        q4.convertBSTToDLL2(treeNode);
        System.out.println("********");

        treeNode = q4.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8});
        q4.convertBSTToDDLLevelOrder(treeNode);

        Random rand = new Random();
        System.out.println(rand.nextInt(1));
        question.fourSum(new int[]{0,0,0,0}, 0);

        node = generateLinkedNode(10);
        q2.reverse(node);
        printLinkedNode(node);
        System.out.println(Arrays.toString(question.radixSort(new int[]{170, 45, 75, 90, 802, 24, 2, 66})));

        question.countAndSay(25);
        //System.out.println(question.footballProblem(4, new int[]{1, 2, 3}, 2));
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 4, 1);
        System.out.println(graph);
        graph.dfs(0);
        graph.bfs(0);
        System.out.println(Arrays.deepToString(graph.shortestPath(0)));
        treeNode = q4.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8});
        q4.printLevelOrder(treeNode);
        q4.preOrder(treeNode);
        System.out.println(q4.getAllPath(treeNode));
        System.out.println(q4.firstCommonAncestor(treeNode, treeNode.left.left, treeNode.left.right).val);
        //System.out.println(q4.firstCommonAncestor2(treeNode, treeNode.left.left, treeNode.left.right).val);
        System.out.println(question.minLenSubStringWithAllChars("adobecodebanc", "abc"));
        question.printAllsubsets("abc");
        System.out.println(question.footballProblem(new int[]{10, 1, 2, 7, 6, 5}, 8, 5));
        question.printAllproducts(new int[]{2, 11, 3});
        question.combinationSum2(new int[]{1, 2, 3, 4, 5}, 5);
        question.combine(4, 2);

    }

    public static void printArray(Object[] arrays) {
        for (Object obj : arrays) {
            System.out.print(obj);
        }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void printLinkedNode(QuestionForLinkedList.LinkedNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(",");
            }
            node = node.next;
        }
        System.out.println();
    }

    public static QuestionForLinkedList.LinkedNode generateLinkedNode(int num) {
        if (num < 1 ) return null;
        int i = 0;
        QuestionForLinkedList.LinkedNode node = new QuestionForLinkedList.LinkedNode(i);
        QuestionForLinkedList.LinkedNode root = node;

        while (++i < num) {
            node.next = new QuestionForLinkedList.LinkedNode(i);
            node = node.next;
        }
        return root;
    }

    public static float getTableMemUsage() {
        System.gc();
        float heapSize = Runtime.getRuntime().totalMemory();
        System.out.println((Runtime.getRuntime().freeMemory()) / 1024.0 / 1024.0);
        List<String[]>  result = new ArrayList<>();
        int i = 0;
        while (i < 100000) {
            result.add(new String[]{"a", "b", "c", "d"});
            i++;
        }


        heapSize = Runtime.getRuntime().totalMemory();
        return (Runtime.getRuntime().freeMemory()) / 1024 / 1024;
    }
}
