package com.hao.interview;

import com.hao.interview.QuestionForTree.TreeNode;
import com.hao.interview.QuestionForLinkedList.LinkedNode;
import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;

import java.net.Inet4Address;
import java.util.*;

/**
 * Created by hzou on 3/15/17.
 */
public class Facebook {

    static class Helper {
        TreeNode head;
        TreeNode prev;
        TreeNode tail;
    }

    public Facebook() {
    }


    public TreeNode createMinimalHeightBST(int[] arrays) {
        return createMinimalHeightBST(arrays, 0, arrays.length - 1);
    }

    public TreeNode createMinimalHeightBST(int[] arrays, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arrays[mid]);
        node.left = createMinimalHeightBST(arrays, start, mid - 1);
        node.right = createMinimalHeightBST(arrays, mid + 1, end);
        return node;
    }

    public TreeNode[] BinaryTreeToDLL(TreeNode root) {
        Helper helper = new Helper();
        BinaryTreeToDLL(root, helper);
        return new TreeNode[]{helper.head, helper.tail};
    }


    public void BinaryTreeToDLL(TreeNode node, Helper helper) {
        if (node == null) return;
        BinaryTreeToDLL(node.left, helper);
        if (helper.head == null) {
            helper.head = node;
        } else {
            node.left = helper.prev;
            helper.prev.right = node;
        }
        helper.prev = node;
        BinaryTreeToDLL(node.right, helper);
        if (node.right == null) {
            helper.tail = node;
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int count = (int) Math.pow(2.0, nums.length);
        for (int i = 0; i < count; i++) {
            List<Integer> oneResult = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((1 << j) & i) > 0) {
                    oneResult.add(nums[j]);
                }
            }
            result.add(oneResult);
        }
        return result;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets2(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void subsets2(int[] nums, int index, List<Integer> oneResult, List<List<Integer>> result) {
        if (index > nums.length) return;
        result.add(new ArrayList<Integer>(oneResult));
        for (int i = index; i < nums.length; i++) {
            oneResult.add(nums[i]);
            subsets2(nums, i + 1, oneResult, result);
            oneResult.remove(oneResult.size() - 1);
        }
    }

    public void printLinkedListBackward(LinkedNode node) {
        LinkedNode cur = node;
        int end = 0;
        while (cur != null) {
            cur = cur.next;
            end++;
        }
        printLinkedListBackward(node, 0, end - 1);
    }

    public void printLinkedListBackward(LinkedNode node, int start, int end) {
        if (node == null) return;
        if (start > end) return;
        int mid = (start + end) / 2;

        if (start == end) {
            LinkedNode midNode = getMidNode(node, mid);
            System.out.println(midNode.val);
            return;
        }
        printLinkedListBackward(node, mid + 1, end);
        printLinkedListBackward(node, start, mid);
    }

    public LinkedNode getMidNode(LinkedNode node, int mid) {
        if (node == null) return null;
        while (mid-- > 0) {
            node = node.next;
        }
        return node;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0; k--) {
            if (j < 0) break;
            if (i < 0) {
                nums1[k] = nums1[j--];
            } else {
                if (nums1[i] < nums2[j]) {
                    nums1[k] = nums2[j--];
                } else {
                    nums1[k] = nums1[i--];
                }
            }
        }
    }

    public void merge(int[] nums, int p, int q, int r) {
        int[] low = new int[q - p + 1];
        int[] high = new int[r - q];
        for (int i = 0, k = p; i < low.length; i++, k++) {
            low[i] = nums[k];
        }
        for (int i = 0, k = q + 1; i < high.length; i++, k++) {
            high[i] = nums[k];
        }
        for (int i = 0, j = 0, k = p; k < low.length + high.length; k++) {
            if (i >= low.length) nums[k] = high[j++];
            else if (j >= high.length) nums[k] = low[i++];
            else {
                if (low[i] < high[j]) {
                    nums[k] = low[i++];
                } else {
                    nums[k] = high[j++];
                }
            }
        }
    }

    public void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    public int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int pivotIndex = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                pivotIndex++;
                int tmp = nums[i];
                nums[i] = nums[pivotIndex];
                nums[pivotIndex] = tmp;
            }
        }
        pivotIndex += 1;
        nums[end] = nums[pivotIndex];
        nums[pivotIndex] = pivot;
        return pivotIndex;
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int pivotIndex = partition(nums, start, end);
        quickSort(nums, start, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }

    public List<List<Integer>> nSum(int[] nums, int target, int n) {
        Arrays.sort(nums);
        return nSum(nums, 0, target, n);
    }

    public List<List<Integer>> nSum(int[] nums, int start, int target, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 2) return result;
        if (n == 2) return twoSum(nums, start, target);
        for (int i = start; i < nums.length - n + 1; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> tempResult = nSum(nums, i + 1, target - nums[i], n - 1);
            for (List<Integer> one : tempResult) {
                one.add(0, nums[i]);
            }
            result.addAll(tempResult);
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start, j = nums.length - 1; i < j; ) {
            int sum = nums[i] + nums[j];
            if (i != start && nums[i - 1] == nums[i]) {
                i++;
                continue;
            }
            if (j != nums.length - 1 && nums[j + 1] == nums[j]) {
                j--;
                continue;
            }
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                List<Integer> oneResult = new ArrayList<>();
                oneResult.add(nums[i++]);
                oneResult.add(nums[j--]);
                result.add(oneResult);
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int targetLeft = target - nums[i];
            for (int start = i + 1, end = nums.length - 1; start < end; ) {
                if (start != i + 1 && nums[start] == nums[start - 1]) {
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

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int targetLeft = target - nums[i];
            for (int start = i + 1, end = nums.length - 1; start < end; ) {
                int sum = nums[start] + nums[end];
                int diff = Math.abs(sum - targetLeft);
                if (sum < targetLeft) {
                    start++;
                } else {
                    end--;
                }
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
        }
        return target - minDiff;
    }


    public int minSubArray(int s, int[] array) {
        if (array == null || array.length == 0) return 0;
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < array.length; i++) {
            sum += array[i];
            while (sum >= s) {
                min = Math.min(min, i - j + 1);
                sum -= array[j++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // Dynamic Problem:

    public int longestNonDescendingNums(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0, j = 1; i < nums.length && j < nums.length; j++) {
            if (nums[j - 1] > nums[j]) {
                max = Math.max(max, j - i);
                i = j;
            }
        }
        return max;
    }

    public int minCoinsNP(int[] coins, int sum) {
        int[] min = new int[sum + 1];
        for (int i = 0; i < sum + 1; i++) {
            min[i] = Integer.MAX_VALUE;
        }
        min[0] = 0;
        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    min[i] = Math.min(min[i - coins[j]] + 1, min[i]);
                }
            }
        }
        return min[sum];
    }

    public List<List<Integer>> minCoins(int[] coins, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        minCoins(coins, 0, sum, new ArrayList<Integer>(), results);
        return results;
    }

    public void minCoins(int[] coins, int start, int sum, List<Integer> oneResult, List<List<Integer>> results) {
        if (sum < 0) return;
        if (sum == 0) {
            results.add(new ArrayList<Integer>(oneResult));
        }

        for (int i = start; i < coins.length; i++) {
            oneResult.add(coins[i]);
            minCoins(coins, start, sum - coins[i], oneResult, results);
            oneResult.remove(oneResult.size() - 1);
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        if (wordDict == null || wordDict.size() == 0) return false;
        boolean[] result = new boolean[s.length() + 1];
        result[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (result[j] && wordDict.contains(s.substring(j, i))) {
                    result[i] = true;
                    break;
                }
            }
        }
        return result[s.length()];
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        wordBreak2(s, "", res, wordDict, new HashMap<String, List<String>>());
        return res;
    }


    public void wordBreak2(String s, String oneResult, List<String> res, List<String> wordDict, Map<String, List<String>> dp) {


        //List<String> words = new ArrayList<String>();
        if (s.length() == 0) {
            res.add(oneResult.trim());
            dp.put(s, new ArrayList<String>(res));
        }

        if (dp.containsKey(s)) return;
        for (int i = 0; i < s.length(); i++) {
            String front = s.substring(0, i + 1);
            String back = s.substring(i + 1);
            if (wordDict.contains(front)) {
                String newOneResult = oneResult + front + " ";
                if (dp.containsKey(back)) {
                    for (String word : dp.get(back)) {
                        newOneResult += word + " ";
                    }
                } else {
                    wordBreak2(back, newOneResult, res, wordDict, dp);
                }
            }
        }
        //dp.put(s, words);
        //return words;
    }

    private static class Result {
        int max;

        public Result(int max) {
            this.max = max;
        }
    }

    public int getMaxNumber(int[][] array) {
        Result result = new Result(0);
        dfs(array, 0, 0, 0, result);
        return result.max;
    }

    public void dfs(int[][] array, int i, int j, int sum, Result result) {
        int height = array.length;
        int width = array[0].length;
        if (i < 0 || j < 0 || i >= height || j >= width) return;

        sum += array[i][j];
        if (i == height - 1 && j == width - 1) {
            result.max = Math.max(result.max, sum);
            return;
        }
        dfs(array, i + 1, j, sum, result);
        dfs(array, i, j + 1, sum, result);
    }

    public int getMaxNumberDP(int[][] array) {
        int height = array.length;
        int width = array[0].length;
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j != 0 && i != 0) {
                    result[i][j] = array[i][j] + Math.max(result[i][j - 1], result[i - 1][j]);
                } else if (i == 0 && j != 0) {
                    result[i][j] = array[i][j] + result[i][j - 1];
                } else if (j == 0 && i != 0) {
                    result[i][j] = array[i][j] + result[i - 1][j];
                } else {
                    result[i][j] = array[i][j];
                }
            }
        }
        return result[height - 1][width - 1];
    }

    public int numDecodings(String s) {
        if (s == null) return 0;
        int[] result = new int[s.length() + 1];
        result[s.length()] = 1;
        result[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            } else {
                result[i] = Integer.parseInt(s.substring(i, i + 2)) < 26 ? result[i + 1] + result[i + 2] : result[i + 1];
            }
        }
        return result[0];
    }


    public int getLongestCommonString(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return 0;
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        dp[0][0] = 0;
        int result = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return result;
    }

    public int numOfIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int height = grid.length;
        int width = grid[0].length;
        boolean[][] visited = new boolean[height][width];
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] array, int i, int j, boolean[][] visited) {
        int height = array.length;
        int width = array[0].length;
        if (i < 0 || j < 0 || i >= height || j >= width || visited[i][j] || array[i][j] == '0') {
            return;
        }
        visited[i][j] = true;
        dfs(array, i + 1, j, visited);
        dfs(array, i - 1, j, visited);
        dfs(array, i, j + 1, visited);
        dfs(array, i, j - 1, visited);
    }

    public int getCompleteTime(String[] tasks, int coolDown) {
        int time = 0;
        Map<String, Integer> taskMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            if (taskMap.containsKey(tasks[i]) && time - taskMap.get(tasks[i]) < coolDown) {
                time = taskMap.get(tasks[i]) + coolDown;
            }
            time++;
            taskMap.put(tasks[i], time);

        }
        return time;
    }

    public static class LexicalComparator<T> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            if (o1 == null || o2 == null) throw new NullPointerException();
            String o1Str = o1.toString();
            String o2Str = o2.toString();
            for (int i = 0; i < o1Str.length() || i < o2Str.length(); i++) {
                if (i == o1Str.length()) return -1;
                if (i == o2Str.length()) return 1;
                if (o1Str.charAt(i) == o2Str.charAt(i)) {
                    continue;
                } else {
                    return o1Str.charAt(i) - o2Str.charAt(i);
                }
            }
            return 0;
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        Collections.sort(list, new LexicalComparator<Integer>());
        return list;
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            preOrder(i, n, result);
        }
        return result;
    }

    public void preOrder(int cur, int n, List<Integer> result) {
        if (cur > n) return;
        result.add(cur);
        for (int i = 0; i < 10; i++) {
            preOrder(cur * 10 + i, n, result);
        }
    }

    public void connectWithExtraSpace(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode prev = null;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (prev != null) {
                    prev.next = node;
                }
                prev = node;
            }
        }
    }

    public void connect(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.right != null && root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
    }

    public void connect2(TreeNode root) {
        if (root == null) return;
        TreeNode head = null;
        TreeNode prev = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                if (prev != null) {
                    prev.next = cur.left;
                } else {
                    head = cur.left;
                }
                prev = cur.left;
            }
            if (cur.right != null) {
                if (prev != null) {
                    prev.next = cur.right;
                } else {
                    head = cur.right;
                }
                prev = cur.right;
            }
            cur = cur.next;
            if (cur == null) {
                cur = head;
                head = null;
                prev = null;
            }
        }
    }

    public void printColumnOrder(TreeNode root) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int index = 0, minIndex = Integer.MAX_VALUE, maxIndex = Integer.MIN_VALUE;
        queue.add(new Pair<TreeNode, Integer>(root, index));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            index = pair.getValue();
            if (map.containsKey(index)) {
                map.get(index).add(node);
            } else {
                List<TreeNode> list = new ArrayList<>();
                list.add(node);
                map.put(index, list);
            }
            if (node.left != null) {
                queue.add(new Pair<>(node.left, index - 1));
            }
            if (node.right != null) {
                queue.add(new Pair<>(node.right, index + 1));
            }
            minIndex = Math.min(index, minIndex);
            maxIndex = Math.max(index, maxIndex);
        }
        for (int i = minIndex; i <= maxIndex; i++) {
            for (TreeNode node : map.get(i)) {
                System.out.print(node.val + " ");
            }
            System.out.println();
        }
    }


    public TreeNode lowestCommonParent(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) return null;

        if (root.val > node1.val && root.val > node2.val) {
            return lowestCommonParent(root.left, node1, node2);
        } else if (root.val < node1.val && root.val < node2.val) {
            return lowestCommonParent(root.right, node1, node2);
        } else {
            return root;
        }
    }

    public int maxPathSum(TreeNode root) {
        Result result = new Result(Integer.MIN_VALUE);
        maxPathSum(root, result);
        return result.max;
    }

    public int maxPathSum(TreeNode root, Result result) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathSum(root.left, result));
        int right = Math.max(0, maxPathSum(root.right, result));
        result.max = Math.max(result.max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        public String toString() {
            return String.format("(%d, %d)", start, end);
        }
    }


    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        boolean isAdded = false;
        for (int i = 0; i < intervals.size(); i++) {


            if (intervals.get(i).end < newInterval.start || intervals.get(i).start > newInterval.end) {
                result.add(intervals.get(i));
            } else {
                newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
                newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            }

            if (!isAdded
                    &&
                    (result.isEmpty() || newInterval.start > result.get(result.size() - 1).end)
                    &&
                    (i + 1 == intervals.size() || i + 1 < intervals.size() && newInterval.end < intervals.get(i + 1).start)) {
                result.add(newInterval);
                isAdded = true;
            }
        }
        if (!isAdded) {
            result.add(0, newInterval);
        }
        return result;
    }

    public boolean validNumer(String str) {
        return str.matches("[\\+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([Ee][\\+-]?\\d+)?");
    }

}
