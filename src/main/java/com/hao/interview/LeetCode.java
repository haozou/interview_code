package com.hao.interview;

import java.util.*;

/**
 * Created by hzou on 8/31/16.
 */
public class LeetCode {
    static public class Interval implements Comparable<Interval>{
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }

        @Override
        public int compareTo(Interval o) {
            return this.start - o.start;
        }
    }

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1.0 / x;

        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return myPow(x, n / Math.abs(n)) * myPow(x * x, n / 2);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        int n = (int) Math.pow(2.0, nums.length);
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((1 << j) & i) > 0) {
                    level.add(nums[j]);
                }
            }
            results.add(level);
        }
        return results;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets2(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void subsets2(int[] nums, int index, ArrayList<Integer> result, List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(result));
        for (int i = index; i < nums.length; i++) {
            result.add(nums[i]);
            ArrayList<Integer> newResult = new ArrayList<>(result);
            subsets2(nums, i + 1, newResult, results);
            //result.remove(result.size() - 1);
        }
    }

    public void subsets3(int[] nums) {
        subsets3(nums, 0, "");
    }

    private void subsets3(int[] nums, int index, String result) {
        System.out.println(result);
        for (int i = index; i < nums.length; i++) {
            String newResult = result + " " + nums[i];
            subsets3(nums, i + 1, newResult);
        }
    }

    public void combinations(int n, int k) {
        combinations(n, k, 1, "");
    }

    private void combinations(int n, int k, int start, String s) {
        if (k < 0) return;
        if (k == 0) {
            System.out.println(s);
            return;
        }
        for (int i = start; i <= n; i++) {
            String newS = s;
            combinations(n, k - 1, i + 1, newS + " " + i);
        }
    }


    public void combinationSum(int[] candidates, int target) {
        combinationSum(candidates, target, 0, "");
    }

    private void combinationSum(int[] candidates, int target, int index, String result) {
        if (target == 0) {
            System.out.println(result);
            return;
        }
        for (int i = index; i < candidates.length && target > 0; i++) {
            String newResult = result +  " " + candidates[i];
            combinationSum(candidates, target - candidates[i], i, newResult);
        }
    }

    public void combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, "");
    }

    private void combinationSum2(int[] candidates, int target, int index, String result) {
        if (target == 0) {
            System.out.println(result);
            return;
        }
        for (int i = index; i < candidates.length && target > 0; i++) {
            if ( i!= index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            String newResult = result +  " " + candidates[i];
            combinationSum2(candidates, target - candidates[i], i + 1, newResult);
        }
    }

    public void combinationSum3(int n, int k) {
        combinationSum3(n, k, 1, "");
    }

    private void combinationSum3(int n, int k, int start, String s) {
        if (k < 0 || n < 0) return;
        if (k == 0 && n == 0) {
            System.out.println(s);
            return;
        }
        for (int i = start; i <= 9; i++) {
            String newS = s;
            combinationSum3(n - i, k - 1, i + 1, newS + " " + i);
        }
    }

    public void combinationSum4(int[] candidates, int target) {
        combinationSum4(candidates, target, 0, "");
    }

    private void combinationSum4(int[] candidates, int target, int index, String result) {
        if (target < 0) return;
        if (target == 0) {
            System.out.println(result);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            String newResult = result +  " " + candidates[i];
            combinationSum4(candidates, target - candidates[i], 0, newResult);
        }
    }

    public List<Interval> mergeIntervals(List<Interval> intervals) {

        List<Interval> result = new ArrayList<>();
        Collections.sort(intervals);
        for (int i = 0; i < intervals.size() - 1;) {
            if (intervals.get(i).end < intervals.get(i + 1).start) {
                result.add(intervals.get(i));
                i++;
            } else {
                intervals.get(i + 1).start = intervals.get(i).start;
                if (intervals.get(i).end > intervals.get(i + 1).end) {
                    intervals.get(i + 1).end = intervals.get(i).end;
                }
                i++;
            }
        }
        result.add(intervals.get(intervals.size() - 1));
        return result;
    }
}
