package com.hao.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzou on 1/17/17.
 */
public class LeetCode2 {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') sum += 1;
            else if (s.charAt(i) == 'V') sum += 5;
            else if (s.charAt(i) == 'X') sum += 10;
            else if (s.charAt(i) == 'L') sum += 50;
            else if (s.charAt(i) == 'C') sum += 100;
            else if (s.charAt(i) == 'D') sum += 500;
            else if (s.charAt(i) == 'M') sum += 1000;
        }
        if (s.contains("IV") || s.contains("IX")) sum -= 2;
        if (s.contains("XL") || s.contains("XC")) sum -= 20;
        if (s.contains("CD") || s.contains("CM")) sum -= 200;
        return sum;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int max = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i] - buy;
            if (cur > 0) {
                max = Math.max(cur, max);
            } else {
                buy = prices[i];
            }
        }
        return max;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int len = 1;
        int tmp = x;
        while ( tmp >= 10) {
            tmp /= 10;
            len *= 10;
        }
        int y = x;
        while (len > 1) {
            if (x % 10 != y / len) return false;
            x = x / 10;
            y = y % len;
            len = len / 10;
        }
        return true;
    }
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) return false;

        Map<Character, Integer> patternMap = new HashMap<>();
        Map<String, Integer> strMap = new HashMap<>();
        //String[] strs = str.split(" ");
        for (int i = 0; i < pattern.length(); i++) {
            if (!patternMap.containsKey(pattern.charAt(i)) && !strMap.containsKey(strs[i])) {
                patternMap.put(pattern.charAt(i), i);
                strMap.put(strs[i], i);
            } else if (patternMap.containsKey(pattern.charAt(i))
                    && strMap.containsKey(strs[i])
                    && patternMap.get(pattern.charAt(i)).equals(strMap.get(strs[i]))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


}
