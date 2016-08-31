package com.hao.interview;

import java.util.*;

/**
 * Created by Hao on 2/24/16.
 */
public class QuestionForOthers {
    public QuestionForOthers(){}

    /**
     * You’re given a board game which is a row of squares, each labeled with an integer.
     * This can be represented by a list, e.g. [1, 3, 2, 0, 5, 2, 8, 4, 1] Given a start
     * position on the board, you “win” by landing on a zero, where you move by jumping
     * from square to square either left or right the number of spaces specified on the
     * square you’re currently on.
     * Your task is to implement the function: canWin(int[] board, pos):
     * returns True if you can win the board from that starting pos, False otherwise
     * @param board
     * @param pos
     * @return
     */
    public boolean canWin(int[] board, int pos) {
        if (board == null || pos >= board.length || pos < 0) return false;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(pos);
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (board[index] == 0) return true;
            visited.add(pos);
            int left = index - board[index];
            int right = index + board[index];
            if (left >= 0 && !visited.contains(left)) {
                queue.add(left);
            }
            if (right < board.length && !visited.contains(right)) {
                queue.add(right);
            }
        }
        return false;
    }
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (pattern.length() != arr.length) return false;
        Map<String, Character> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                if (!hashMap.get(arr[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            } else {
                if (hashMap.containsValue(pattern.charAt(i))){
                    return false;
                } else {
                    hashMap.put(arr[i], pattern.charAt(i));
                }

            }
        }
        return true;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 4, 0, target);
    }
    public List<List<Integer>> nSum(int[] nums, int n, int idx, int target) {
        if (n == 2) return twoSum(nums, idx,  target);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = idx; i <= nums.length - n;) {
            List<List<Integer>> temp = nSum(nums, n - 1, i + 1, target - nums[i]);
            for (List<Integer> l : temp) {
                l.add(0, nums[i]);
            }
            result.addAll(temp);
            int t = nums[i];
            while (i <= nums.length - n && nums[i] == t) {
                i++;
            }
        }
        return result;
    }
    public List<List<Integer>> twoSum(int[] nums, int idx, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = idx, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                List<Integer> level = new ArrayList<Integer>();
                level.add(nums[i]);
                level.add(nums[j]);
                result.add(level);
                int tmp = nums[i];
                while ( i < j && tmp == nums[i]) {
                    i++;
                }
            }
        }
        return result;
    }
    private double MO2(int a, int b) {
        return (a + b) / 2.0;
    }

    private double MO3(int a, int b, int c) {
        return a + b + c
                - Math.max(Math.max(a, b), c)
                - Math.min(Math.min(a, b), c);
    }
    private double MO4(int a, int b, int c, int d) {
        return (a + b + c + d
                - Math.max(Math.max(Math.max(a, b), c), d)
                - Math.min(Math.min(Math.min(a, b), c), d)) / 2.0;
    }
    private double findMedianUtil(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
        if (end1 - start1 == 0) {
            if (end2 - start2 == 0) {
                return MO2(arr1[start1], arr2[start2]);
            } else if ((end2 - start2 + 1) % 2 > 0) {
                return MO2(arr2[(start2 + end2) / 2], (int)MO3(arr1[start1], arr2[(start2 + end2) / 2 - 1], arr2[(start2 + end2) / 2 + 1]));
            } else {
                return MO3(arr1[start1], arr2[(start2 + end2) / 2], arr2[(start2 + end2) / 2 + 1]);
            }
        } else if (end1 - start1 == 1) {
            if (end2 - start2 == 1) {
                return MO4(arr1[start1], arr1[end1], arr2[start2], arr2[end2]);
            } else if ((end2 - start2 + 1) % 2 > 0) {
                return MO3(arr2[(start2 + end2) / 2], Math.max(arr1[start1], arr2[(start2 + end2) / 2 - 1]), Math.min(arr1[end1], arr2[(start2 + end2) / 2 + 1]));
            } else {
                return MO4(arr2[(start2 + end2) / 2], arr2[(start2 + end2) / 2 + 1], Math.max(arr1[start1], arr2[(start2 + end2) / 2 - 1]), Math.min(arr1[end1], arr2[(start2 + end2) / 2 + 2]));
            }
        }
        int i = (end1 + start1) / 2;
        int j = (end2 + start2) / 2;
        if (arr1[i] <= arr2[j]) {
            return findMedianUtil(arr1, i, end1, arr2, start2, end2 - (i - start1));
        } else {
            return findMedianUtil(arr1, start1, (end1 + start1 + 1) / 2, arr2, start2 + (end1 - (end1 + start1 + 1) / 2), end2);
        }
    }
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        if (arr1.length <= arr2.length) {
            return findMedianUtil(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
        } else {
            return findMedianUtil(arr2, 0, arr2.length - 1, arr1, 0, arr1.length - 1);
        }
    }

    public String longestPalSubStr(String s) {
        if (s == null) return null;
        int n = s.length();
        if (n == 0) return null;
        int start = 0;
        int maxLength = 1;

        boolean[][] table = new boolean[1000][1000];
        /* single char is palindrome*/
        for (int i = 0; i < n; i++) {
            table[i][i] = true;
        }
        /* check for sub-string of length 2 */
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                table[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        /* check for sub-string of length greater than 2 */
        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if (table[i+1][j-1] == true && s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    start = i;
                    maxLength = k;
                }
            }
        }

        return s.substring(start, maxLength + start);
    }
}
