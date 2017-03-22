package com.hao.interview;

import com.hao.interview.imageProcess.ImageManipulator;
import org.testng.annotations.Test;
import org.testng.Assert.*;
import com.hao.interview.QuestionForTree.TreeNode;
import com.hao.interview.QuestionForLinkedList.LinkedNode;

import java.util.Arrays;

/**
 * Created by hzou on 3/15/17.
 */

@Test
public class FacebookTest {
    private Facebook facebook = new Facebook();

    private void inOrderBST(TreeNode node) {
        if (node == null) return;
        inOrderBST(node.left);
        System.out.print(node.val + " ");
        inOrderBST(node.right);
    }

    private void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void testCreateMinimalHeightDDL() {
        TreeNode root = facebook.createMinimalHeightBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        inOrderBST(root);
        System.out.println();
    }

    @Test
    public void testBinaryTreeToDLL() {
        TreeNode root = facebook.createMinimalHeightBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        TreeNode[] nodes = facebook.BinaryTreeToDLL(root);
        TreeNode head = nodes[0];
        TreeNode tail = nodes[1];
        for (TreeNode cur = head; cur != null; cur = cur.right) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

        for (TreeNode cur = tail; cur != null; cur = cur.left) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }

    @Test
    public void testSubsets() {
        System.out.println(facebook.subsets1(new int[]{1, 2, 3}));
        System.out.println(facebook.subsets2(new int[]{1, 2, 3}));
    }

    @Test
    public void testPrintLinkedListBackward() {
        LinkedNode node = new LinkedNode(1);
        node.next = new LinkedNode(2);
        node.next.next = new LinkedNode(3);
        node.next.next.next = new LinkedNode(4);

        facebook.printLinkedListBackward(node);
    }

    @Test
    public void testMergeSort() {
        int[] nums = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8};
        facebook.merge(nums, 0, 4, 8);
        printArray(nums);

        nums = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8};
        facebook.mergeSort(nums);
        printArray(nums);

    }

    @Test
    public void testQuickSort() {
        int[] nums = new int[]{1, 3, 5, 7, 9, 2, 4, 8, 6};
        facebook.partition(nums, 0, 8);
        printArray(nums);

        nums = new int[]{1, 3, 5, 7, 9, 2, 4, 8, 6};
        facebook.quickSort(nums);
        printArray(nums);
    }

    @Test
    public void testNSum() {
        int[] nums = new int[]{1, 3, 3, 5, 5, 7, 9, 2, 4, 8, 6};
        System.out.println(facebook.nSum(nums, 9, 3));
        System.out.println(facebook.threeSum(nums, 9));
    }

    @Test
    public void testEnlarge() {
        int[][] array = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 2, 3, 4, 5, 1, 1},
                {1, 1, 2, 3, 4, 5, 1, 1},
                {1, 1, 2, 3, 4, 5, 1, 1},
                {1, 1, 2, 3, 4, 5, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

//        array = new int[][] {
//                {1, 1, 1, 1},
//                {1, 2, 3, 1},
//                {1, 2, 3, 1},
//                {1, 1, 1, 1}
//        };
        ImageManipulator imageManipulator = new ImageManipulator();
        int[][] result = imageManipulator.enlarge(array);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }
}
