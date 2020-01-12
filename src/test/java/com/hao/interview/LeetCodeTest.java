package com.hao.interview;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by hzou on 8/31/16.
 */
public class LeetCodeTest {
    private LeetCode leetCode;
    @BeforeTest
    public void init() {
        leetCode = new LeetCode();
    }
    @Test
    public void testMyPow() {

        assertEquals(16.0, leetCode.myPow(2.0, 4));
        assertEquals(32.0, leetCode.myPow(2.0, 5));
        assertEquals(2.0, leetCode.myPow(2.0, 1));
        System.out.println(leetCode.myPow(2.0, Integer.MIN_VALUE));
        assertEquals(Math.pow(2.0, Integer.MIN_VALUE), leetCode.myPow(2.0, Integer.MIN_VALUE));
    }

    @Test
    public void testSubsets() {

        System.out.println(leetCode.subsets(new int[]{1, 2, 3}));
        System.out.println(leetCode.subsets2(new int[]{1, 2, 3}));
        leetCode.subsets3(new int[]{1, 2, 3});
    }

    @Test
    public void testCombinations() {
        leetCode.combinations(4, 2);
        leetCode.combinationSum(new int[]{2, 3, 6, 7}, 7);
        leetCode.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println("-----");
        leetCode.combinationSum3(9, 3);
        System.out.println("-----");
        leetCode.combinationSum4(new int[]{1, 2, 3}, 4);
        System.out.println("-----");
    }

    @Test
    public void testMergeIntervals() {
        List<LeetCode.Interval> intervals = new ArrayList<>();
        intervals.add(new LeetCode.Interval(1, 3));
        intervals.add(new LeetCode.Interval(2, 6));
        intervals.add(new LeetCode.Interval(8, 10));
        intervals.add(new LeetCode.Interval(15, 18));
        intervals.add(new LeetCode.Interval(16, 17));
        System.out.println(leetCode.mergeIntervals(intervals));
    }

    @Test
    public void testIsomorphic() {
        assertTrue(leetCode.isomorphic("egg", "add"));
        assertFalse(leetCode.isomorphic("eggg", "adde"));
    }

    @Test
    public void testNSum() {
        System.out.println(leetCode.nSum(new int[]{-4, -1, -1, 0, 1, 2}, 0, 3));
    }

    @Test
    public void testKClosest() {
        leetCode.kClosest(new int[]{12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}, 4, 35);
    }

    @Test
    public void testMerge() {
        int[] nums1 = new int[100];
        for (int i =0 ; i < 6; i++) {
            nums1[i] = i + 2;
        }
        leetCode.merge(nums1, 6, new int[]{1,2,3,4,5,6,7}, 7);
        for (int i = 0; i < 13; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void testShortestWordDistance() {
        System.out.println(leetCode.shortestWordDistance(new String[]{"abc", "bad", "good", "how", "bad"}, "abc", "bad"));
        System.out.println(leetCode.shortestWordDistance(new String[]{"abc", "bad", "good", "how", "bad"}, "abc", "good"));
        System.out.println(leetCode.shortestWordDistance(new String[]{"abc", "bad", "good", "how", "bad"}, "good", "good"));


        LeetCode.ShortestDistance shortestDistance = leetCode.new ShortestDistance(new String[]{"abc", "bad", "good", "how", "bad"});
        System.out.println(shortestDistance.shortest("abc", "bad"));
        System.out.println(shortestDistance.shortest("abc", "good"));

    }

    @Test
    public void testevalRPN() {
        System.out.println(leetCode.evalRPN(new String[]{"4","13","5","/","+"}));
    }

    @Test
    public void testReverseWord() {
        char[] words = "the sky is blue".toCharArray();
        leetCode.reverseWords(words);
        System.out.println(words);
    }

    @Test
    public void testTopK() {
        System.out.println(leetCode.topKFrequent(new int[]{1,1,1,1,2,2,2,3}, 2));
        System.out.println(leetCode.findKthLargest(new int[]{1,1,1,1,2,2,2,3,4,5}, 2));
    }

    @Test
    public void testFactor() {
        System.out.println(leetCode.factorCombinations(8));
    }

    @Test
    public void testIsWordPattern() {
        System.out.println(leetCode.isWordPattern("abcc", "ab de fg fg"));
    }

    @Test
    public void testCreateTree() {
        /**
         *  5
         / \
         4   8
         /   / \
         11  13  4
         /  \    / \
         7    2  5   1
         */
        LeetCode.TreeNode node = leetCode.createTree(new String[]{"5","4","8","11",null,"13","4","7","2",null,null,"5","1"});
        System.out.println(leetCode.levelOrder(node));

    }

    @Test
    public void testPathSum() {
        LeetCode.TreeNode root = leetCode.createTree(new String[]{"5","4","8","11",null,"13","4","7","2",null,null,"5","1"});
        System.out.println(leetCode.pathSum(root, 22));
    }

    @Test
    public void testPrintAllPath() {
        LeetCode.TreeNode node = leetCode.createTree(new String[]{"5","4","8","11",null,"13","4","7","2",null,null,"5","1"});

        leetCode.printAllPaths(node);
    }
}
