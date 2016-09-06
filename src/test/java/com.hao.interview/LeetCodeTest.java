package com.hao.interview;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
}
