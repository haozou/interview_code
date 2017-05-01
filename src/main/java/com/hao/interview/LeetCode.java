package com.hao.interview;

import java.net.Inet4Address;
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

    public Boolean isomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] sAddrs = new int[128];
        int[] tAddrs = new int[128];
        for (int i = 0; i < s.length(); i++) {
            if (sAddrs[s.charAt(i)] != tAddrs[t.charAt(i)]) {
                return false;
            } else if (sAddrs[s.charAt(i)] == 0) {
                sAddrs[s.charAt(i)] = i + 1;
                tAddrs[t.charAt(i)] = i + 1;
            }
        }
        return true;
    }

    public Boolean isWordPattern(String pattern, String words) {
        if (pattern == null || words == null) return false;

        char[] patterns = pattern.toCharArray();
        String[] wordsArray = words.split(" ");
        if (patterns.length != wordsArray.length) return false;

        String[] patternString = new String[128];
        HashSet<String> wordAddrs = new HashSet<>();
        for (int i = 0; i < patterns.length; i++) {
            if (patternString[patterns[i]] == null) {
                if (wordAddrs.contains(wordsArray[i])) {
                    return false;
                }
                patternString[patterns[i]] = wordsArray[i];
                wordAddrs.add(wordsArray[i]);
            } else {
                if (!wordsArray[i].equals(patternString[patterns[i]])) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0, j = nums.length - 1; i < j;) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{i, j};
            }
        }
        return null;
    }

    public static class TwoSum3 {
        private Map<Integer, List<Integer>> map;
        private int index;
        public TwoSum3() {
            map = new HashMap<>();
            index = 0;
        }

        public void add(int num) {
            if (map.containsKey(num)) {
                map.get(num).add(index);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(index);
                map.put(num, indexes);
            }
            index++;
        }

        public int[] find(int target) {
            for (Integer i: map.keySet()) {
                if (map.containsKey(target - i)) {
                    if (i == target && map.get(target - i).size() < 2) {
                        continue;
                    }
                    return new int[]{i, map.get(target - i).get(1)};
                }
            }
            return null;
        }
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i - 1] == nums[i]) continue;
            int targetLeft = target - nums[i];
            for (int start = i + 1, end = nums.length - 1; start < end;) {
                if (start != i + 1 && nums[start - 1] == nums[start]) {
                    start++;
                    continue;
                }

                if (end != nums.length - 1 && nums[end] == nums[end + 1]) {
                    end--;
                    continue;
                }
                int sum = nums[start] + nums[end];
                if (sum < targetLeft) {
                    start++;
                } else if (sum > targetLeft) {
                    end--;
                } else {
                    List<Integer> oneResult = new ArrayList<>();
                    oneResult.add(nums[i]);
                    oneResult.add(nums[start]);
                    oneResult.add(nums[end]);
                    result.add(oneResult);
                    start++;
                    end--;
                }
            }
        }
        return result;
    }

    public int closestThreeSum(int[] nums, int target) {
        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int targetLeft = target - nums[i];
            for (int start = i + 1, end = nums.length - 1; start < end;) {

                int sum = nums[start] + nums[end];
                int diff = Math.abs(sum - targetLeft);
                if (sum < targetLeft) {
                    start++;
                } else {
                    end--;
                }

                if (diff < minDiff) {
                    minDiff = diff;
                    result = sum;
                }

            }
        }
        return result;
    }

    public List<List<Integer>> nSum(int[] nums, int target, int n) {
        Arrays.sort(nums);
        return nSum(nums, 0, target, 3);
    }
    private List<List<Integer>> nSum(int[] nums, int idx, int target, int n) {
        if (n < 2) return null;
        if (n == 2) return twoSum(nums, idx, target);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = idx; i < nums.length - n + 1; i++) {
            if (i != idx && nums[i - 1] == nums[i]) {
                continue;
            }
            List<List<Integer>> tmpResult = nSum(nums, i + 1, target - nums[i], n - 1);
            for (List<Integer> oneResult: tmpResult) {
                oneResult.add(0, nums[i]);
            }
            result.addAll(tmpResult);
        }
        return result;
    }
    private List<List<Integer>> twoSum(int[] nums, int idx, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int start = idx, end = nums.length - 1; start < end;) {
            if (start != idx && nums[start - 1] == nums[start]) {
                start++;
                continue;
            }
            if (end != nums.length - 1 && nums[end + 1] == nums[end]) {
                end--;
                continue;
            }
            int sum = nums[start] + nums[end];
            if (sum < target) {
                start++;
            } else if (sum > target) {
                end--;
            } else {
                List<Integer> oneResult = new ArrayList<>();
                oneResult.add(nums[start]);
                oneResult.add(nums[end]);
                result.add(oneResult);
                start++;
                end--;
            }

        }
        return result;
    }

    public void kClosest(int[] nums, int k, int x) {
        int start = 0, end = nums.length - 1, mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] > x) {
                end = mid - 1;
            } else if (nums[mid] < x) {
                start = mid + 1;
            } else {
                break;
            }
        }
        int crossIndex = mid;
        if (start == end) {
            crossIndex = start;
        }
        int l = crossIndex, r = l + 1;
        while (l >= 0 && r < nums.length && k > 0) {
            if (Math.abs(nums[l] - x) > Math.abs(nums[r] - x)) {
                System.out.print(nums[r++] + " ");
            } else {
                System.out.print(nums[l--] + " ");
            }
            k--;
        }

        while (k > 0 && l >= 0) {
            System.out.print(nums[l--] + " ");
            k--;
        }

        while (k > 0 && r < nums.length) {
            System.out.print(nums[r++] + " ");
            k--;
        }
        System.out.println();
    }

    public String integerToRoman(int val) {
        if (val >= 1000) return "M" + integerToRoman(val - 1000);
        if (val >= 900) return "CM" + integerToRoman(val - 900);
        if (val >= 500) return "D" + integerToRoman(val - 500);
        if (val >= 400) return "CD" + integerToRoman(val - 400);
        if (val >= 100) return "C" + integerToRoman(val - 100);
        if (val >= 90) return "XC" + integerToRoman(val - 90);
        if (val >= 50) return "L" + integerToRoman(val - 50);
        if (val >= 40) return "XL" + integerToRoman(val - 40);
        if (val >= 10) return "X" + integerToRoman(val - 10);
        if (val >= 9) return "IX" + integerToRoman(val - 9);
        if (val >= 5) return "V" + integerToRoman(val - 5);
        if (val >= 4) return "IV" + integerToRoman(val - 4);
        if (val >= 1) return "I" + integerToRoman(val - 1);
        return "";
    }

    public int romanToInteger(String roman) {
        int val = 0;
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            switch (c) {
                case 'I': {
                    val += 1;
                    break;
                }
                case 'V': {
                    val += 5;
                    break;
                }
                case 'X': {
                    val += 10;
                    break;
                }
                case 'L': {
                    val += 50;
                    break;
                }
                case 'C': {
                    val += 100;
                    break;
                }
                case 'D': {
                    val += 500;
                    break;
                }
                case 'M': {
                    val += 1000;
                    break;
                }
                default:
                    break;
            }
        }
        if (roman.contains("IV")) {
            val -= 2;
        }
        if (roman.contains("IX")) {
            val -= 2;
        }
        if (roman.contains("XL")) {
            val -= 20;
        }
        if (roman.contains("XC")) {
            val -= 20;
        }
        if (roman.contains("CD")) {
            val -= 200;
        }
        if (roman.contains("CM")) {
            val -= 200;
        }
        return val;
    }

    public boolean validParentheses(String s) {
        Map<Character, Character> acceptedParantheses = new HashMap<Character, Character>() {
            {
                put('{', '}');
                put('[', ']');
                put('(', ')');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (acceptedParantheses.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (acceptedParantheses.containsValue(s.charAt(i))) {
                    if (stack.isEmpty() || acceptedParantheses.get(stack.pop()) != s.charAt(i)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode head = dummyNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                dummyNode.next = l2;
                l2 = l2.next;
                continue;
            } else if (l2 == null) {
                dummyNode.next = l1;
                l1 = l1.next;
                continue;
            } else {
                if (l1.val < l2.val) {
                    dummyNode.next = l1;
                    l1 = l1.next;
                } else {
                    dummyNode.next = l2;
                    l2 = l2.next;
                }
            }
            dummyNode = dummyNode.next;
        }
        return head.next;
    }

    public int searchRotate(int[] A, int target) {
        if (A == null) return -1;
        for (int left = 0, right = A.length - 1; left < right; ) {
            int mid = (left + right) / 2;
            if (target < A[mid]) {
                if (A[mid] > A[right] && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (target > A[mid]) {
                if (A[mid] < A[left] && target >= A[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int searchRotate2(int[] A, int target) {
        if (A == null) return -1;
        for (int left = 0, right = A.length - 1; left <= right; ) {
            int mid = (left + right) / 2;
            if (target < A[mid]) {
                if (A[mid] > A[right] && target <= A[right]) {
                    left = mid + 1;
                } else {
                    if (A[mid] == A[left]) {
                        left++;
                    } else {
                        right = mid - 1;
                    }
                }
            } else if (target > A[mid]) {
                if (A[mid] < A[left] && target >= A[left]) {
                    right = mid - 1;
                } else {
                    if (A[mid] == A[right]) {
                        right--;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int maxSubArray(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        boolean allNegative = false;
        for (int val: A) {
            sum += val;
            if (sum < 0) {
                allNegative = true;
            }
            max = Math.max(val, max);
        }
        if (allNegative) return max;

        sum = 0;
        for (int val: A) {
            sum += val;
            if (sum < 0) sum = 0;
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public int sqrt(int x) {
        long i = 0, j = x;
        for (; i <= j;) {
            long mid = (i + j) / 2;
            long result = mid * mid;
            if (result == x) {
                return (int)mid;
            } else if (result > x) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return (int)j;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int k = m + n - 1, i = m - 1, j = n - 1; k >= 0;) {
            if (j < 0) {
                break;
            } else if (i < 0) {
                nums1[k--] = nums2[j--];
            } else {
                if (nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
             }
        }
    }

    public int shortestWordDistance(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(word2)) {
                if (word1.equals(words[i])) {
                    if (idx1 > idx2) idx2 = i;
                    else idx1 = i;
                }
            } else {
                if (words[i].equals(word1)) {
                    idx1 = i;
                } else if (words[i].equals(word2)) {
                    idx2 = i;
                }
            }
            if (idx1 != -1 && idx2 != -1) {
                min = Math.min(min, Math.abs(idx1 - idx2));
            }
        }
        return min;
    }

    class ShortestDistance {
        private String[] words;
        private HashMap<String, List<Integer>> wordsMap;
        public ShortestDistance(String[] words) {
            this.words = words;
            this.wordsMap = new HashMap<>();
            int i = 0;
            for (String word: words) {
                if (this.wordsMap.containsKey(word)) {
                    this.wordsMap.get(word).add(i++);
                } else {
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i++);
                    this.wordsMap.put(word, indexes);
                }
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> idxes1 = this.wordsMap.get(word1);
            List<Integer> idxes2 = this.wordsMap.get(word2);
            int min = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < idxes1.size() && j < idxes2.size();) {
                min = Math.min(min, Math.abs(idxes1.get(i) - idxes2.get(j)));
                if (idxes1.get(i) < idxes2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return min;
        }
    }

    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null && node1.val == node2.val) {
            return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
        }
        return false;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token: tokens) {
            if (token.matches("[+|-]?[0-9]+$")){
                stack.push(Integer.valueOf(token));
            } else {
                if (stack.size() < 2) throw new RuntimeException("invalid");
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (token) {
                    case "+" :
                        stack.push(val1 + val2);
                        break;
                    case "-" :
                        stack.push(val2 - val1);
                        break;
                    case "*" :
                        stack.push(val1 * val2);
                        break;
                    case "/":
                        stack.push(val2 / val1);
                        break;
                    default:
                        throw new RuntimeException("invalid");
                }
            }
        }
        if (stack.isEmpty()) throw new RuntimeException("invalid");
        return stack.pop();
    }

    public TreeNode upsideDown(TreeNode root) {
        TreeNode node = root, parent = null, right = null, left = null;
        while (node != null) {
            left = node.left;
            node.left = right;
            right = node.right;
            node.right = parent;
            parent = node;
            node = left;
        }
        return parent;
    }

    public void reverseWords(char[] words) {
        if (words == null) return;
        reverse(words, 0 ,words.length - 1);
        int start = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == ' ') {
                reverse(words, start, i - 1);
                start = i + 1;
            }
        }
        reverse(words, start, words.length - 1);
    }

    private void reverse(char[] words, int start, int end) {
        while (start < end) {
            char tmp = words[start];
            words[start] = words[end];
            words[end] = tmp;
            start++;
            end--;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> numWithFrequencies = new PriorityQueue<int[]> (nums.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            numWithFrequencies.add(new int[]{entry.getKey(), entry.getValue()});
        }
        while (k-- > 0 && !numWithFrequencies.isEmpty()) {
            result.add(numWithFrequencies.poll()[0]);
        }
        return result;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer> (nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(nums[i]);
        }
        while (k-- > 1 && !priorityQueue.isEmpty()) {
            priorityQueue.poll();
        }
        return priorityQueue.poll();
    }

    public List<List<Integer>> factorCombinations(int n) {
        List<List<Integer>> results = new ArrayList<>();
        factorCombinations(n, 2, new ArrayList<Integer>(), results);
        return results;
    }

    public void factorCombinations(int n, int start, List<Integer> oneResult, List<List<Integer>> results) {
        if (n == 1) {
            if (oneResult.size() > 1) {
                results.add(new ArrayList<>(oneResult));
            }
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                oneResult.add(i);
                factorCombinations(n / i, i, oneResult, results);
                oneResult.remove(oneResult.size() - 1);
            }
        }
    }

    public List<List<Integer>> printTreeLeaf(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = remove(root, leaves);
            result.add(leaves);
        }
        return result;
    }

    public TreeNode remove(TreeNode node, List<Integer> leaves) {
        if (node == null) return null;
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return null;
        }
        node.left = remove(node.left, leaves);
        node.right = remove(node.right, leaves);
        return node;
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                start *= nums[j];
                max = Math.max(start, max);
            }
        }
        return max;
    }

    public TreeNode createTree(String[] array) {
        int[] numOfNull = new int[array.length];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)  count++;
            numOfNull[i] = count;
        }
        return createTree(array, 0, numOfNull);
    }
    //[5,4,8,11,null,13,4,7,2,null,null,5,1]
    public TreeNode createTree(String[] array, int i, int[] numOfNull) {
        if (i >= array.length) return null;
        String val = array[i];
        if (val == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = createTree(array, i + 1 + i - numOfNull[i] * 2 , numOfNull);
        node.right = createTree(array, i + 2 + i - numOfNull[i] * 2, numOfNull);
        return node;
    }

    public void printAllPaths(TreeNode node) {
        printAllPaths(node, "");
    }
    public void printAllPaths(TreeNode node, String path) {
        if(node == null) return;
        String newPath = path + node.val + " ";
        if (node.left == null && node.right == null) {
            System.out.println(newPath);
            return;
        }
        printAllPaths(node.left, newPath);
        printAllPaths(node.right, newPath);

    }

    public List<List<Integer>> pathSum(TreeNode node, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(node, sum, new ArrayList<Integer>(), result);
        return result;
    }

    public void pathSum(TreeNode node, int sum, List<Integer> oneResult, List<List<Integer>> result) {
        if (node == null) return;
        sum -= node.val;
        oneResult.add(node.val);
        if (sum == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<Integer>(oneResult));
            oneResult.remove(oneResult.size() - 1);
            return;
        }

        pathSum(node.left, sum, oneResult, result);
        pathSum(node.right, sum, oneResult, result);
        oneResult.remove(oneResult.size() - 1);
    }
}
