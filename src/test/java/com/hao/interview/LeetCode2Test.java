package com.hao.interview;

import org.testng.annotations.Test;

/**
 * Created by hzou on 1/17/17.
 */
public class LeetCode2Test {
    LeetCode2 leetCode2 = new LeetCode2();

    @Test
    public void testMaxProfit() {
        System.out.println(leetCode2.maxProfit(new int[]{7,1,5,3,6,4}));

    }

    @Test
    public void testIsPalindrome() {

        System.out.println(leetCode2.isPalindrome(313));
    }

    @Test
    public void testWordPattern() {
        System.out.println(leetCode2.wordPattern("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd",
                "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t"));
    }

}
