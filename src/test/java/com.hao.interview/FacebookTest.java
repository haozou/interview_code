package com.hao.interview;

import com.hao.interview.QuestionForLinkedList.LinkedNode;
import com.hao.interview.QuestionForTree.TreeNode;
import com.hao.interview.imageProcess.ImageManipulator;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        TreeNode root = facebook.createMinimalHeightBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        inOrderBST(root);
        System.out.println();
        facebook.printColumnOrder(root);
        root.left.left.left = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(0);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(8);
        facebook.connect2(root);
        printTree(root);

    }
    public void printTree(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
        if (root.left != null)
            printTree(root.left);
        else
            printTree(root.right);
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
        System.out.println(facebook.subsetsWithDup(new int[]{1,2,2}));
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

    @Test
    public void testWordBreak2() {
        String s = "catsanddog";
        String[] dict = new String[]{"cat", "cats", "and", "sand", "dog"};
        System.out.println(facebook.wordBreak2(s, Arrays.asList(dict)));
    }

    @Test
    public void testMinCoins() {
        System.out.println(facebook.minCoins(new int[]{1,3,5}, 11));
        System.out.println(facebook.minCoinsNP(new int[]{1,3,5}, 11));
    }

    @Test
    public void testLongestNonDescendingNums() {
        System.out.println(facebook.longestNonDescendingNums(new int[]{5,1,2,3,2,4,6,8,10,3,5}));
    }

    @Test
    public void testGetMaxNumber() {
        System.out.println(facebook.getMaxNumber(new int[][]{
                {1,2,2,3},
                {3,4,3,3},
                {1,2,3,3}
        }));
        System.out.println(facebook.getMaxNumberDP(new int[][]{
                {1,2,2,3},
                {3,4,3,3},
                {1,2,3,3}
        }));
    }

    @Test
    public void testGetCompleteTime() {
        String[] tasks = new String[]{"A", "B", "B", "C", "A", "A"};
        System.out.println(facebook.getCompleteTime(tasks, 3));
    }

    @Test
    public void testLexicalOrder() {
        System.out.println(facebook.lexicalOrder(13));
        System.out.println(facebook.lexicalOrder2(13));
    }

    @Test
    public void testGetLongestCommonString() {
        System.out.println(facebook.getLongestCommonString("abcdcommon", "dco"));
    }

    @Test
    public void testInsertInterval() {
        List<Facebook.Interval> intervals = new ArrayList<>(Arrays.asList(
                new Facebook.Interval(1, 2),
                new Facebook.Interval(3, 5),
                new Facebook.Interval(6, 8),
                new Facebook.Interval(10, 12),
                new Facebook.Interval(13, 15)
        ));
        System.out.println(facebook.insert(intervals, new Facebook.Interval(4, 7)));
    }

    @Test
    public void testLongestSubString() {
        System.out.println(facebook.longestSubstring("ababbc", 2));
    }

}
