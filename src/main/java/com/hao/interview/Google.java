package com.hao.interview;

import com.hao.interview.QuestionForTree.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by hzou on 10/2/16.
 */
public class Google<T> {
    public Google() {
    }

    public boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        for (; i < a.length() && j < b.length(); ) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i == a.length()) return true;
        return false;
    }

    public boolean isStartWithUpperCase(String str) {
        if (str == null || str.length() == 0) return false;
        return str.matches("^[A-Z].*");
    }

    public boolean isBST(TreeNode treeNode, int min, int max) {
        if (treeNode == null) return true;

        if (treeNode.val < min || treeNode.val > max) {
            return false;
        }
        return isBST(treeNode.left, min, treeNode.val) && isBST(treeNode.right, treeNode.val, max);
    }

    public void addShortestDist(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'G' && !visited[i][j]) {
                    bfs(matrix, i, j, visited);
                    return;
                }
            }
        }

    }

    public void bfs(char[][] matrix, int x, int y, boolean[][] visited) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        //boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            x = element[0];
            y = element[1];
            int step = element[2];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == 'B' || visited[x][y]) continue;
            char c = matrix[x][y];
            if (c != 'G') {
                if (c == '0' || c - '0' > step) {
                    matrix[x][y] = Character.forDigit(step, 10);
                } else {
                    continue;
                }
            } else {
                visited[x][y] = true;
                step = 0;
            }
            queue.add(new int[]{x + 1, y, step + 1});
            queue.add(new int[]{x, y + 1, step + 1});
            queue.add(new int[]{x - 1, y, step + 1});
            queue.add(new int[]{x, y - 1, step + 1});
        }
    }

    public List<List<Integer>> combinations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        combinations(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void combinations(int[] nums, int index, List<Integer> oneResult, List<List<Integer>> result) {
        //if (index >= nums.length) return;
        result.add(new ArrayList<Integer>(oneResult));
        for (int i = index; i < nums.length; i++) {
            //if (i + 1 <  nums.length && nums[i] == nums[i + 1]) continue;
            oneResult.add(nums[i]);
            combinations(nums, i + 1, oneResult, result);
            oneResult.remove(oneResult.size() - 1);
        }
    }

//    public List<List<Integer>> permutations(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        permutations(nums, 0, nums.length - 1, result);
//        return result;
//    }
//
//    public void permutations(final int[] nums, int start, int end, List<List<Integer>> result) {
//        if (start == end) {
//            result.add(new ArrayList<Integer>(){{for(int num: nums) add(num);}});
//        } else {
//            for (int i = start; i <= end; i++) {
//                swap(nums, start, i);
//                permutations(nums, start + 1, end, result);
//                swap(nums, start, i);
//            }
//        }
//    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    public List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutations(nums, new ArrayList<Integer>(), result);
        return result;
    }

    public void permutations(int[] nums, ArrayList<Integer> oneResult, List<List<Integer>> result) {
        if (nums.length == oneResult.size()) {
            result.add(new ArrayList<Integer>(oneResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (oneResult.contains(nums[i])) continue;
            oneResult.add(nums[i]);
            permutations(nums, oneResult, result);
            oneResult.remove(oneResult.size() - 1);
        }
    }


    public List<List<Integer>> permutationNonUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permutationNonUnique(nums, new boolean[nums.length], new ArrayList<Integer>(), result);
        return result;
    }

    public void permutationNonUnique(int[] nums, boolean[] used, List<Integer> oneResult, List<List<Integer>> result) {
        if (nums.length == oneResult.size()) {
            result.add(new ArrayList<Integer>(oneResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            oneResult.add(nums[i]);
            permutationNonUnique(nums, used, oneResult, result);
            used[i] = false;
            oneResult.remove(oneResult.size() - 1);
        }
    }


    public void stringPermutation(String str) {
        permute(str, 0, str.length() - 1);
    }

    /**
     * permutation function
     *
     * @param str string to calculate permutation for
     * @param l   starting index
     * @param r   end index
     */
    private void permute(String str, int l, int r) {
        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public void nextPermute(Integer[] nums) {
        int m = -1, n = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                m = i - 1;
                break;
            }
        }
        if (m != -1) {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] > nums[m]) {
                    n = i;
                    break;
                }
            }
            int tmp = nums[m];
            nums[m] = nums[n];
            nums[n] = tmp;
        }
        for (int i = m + 1, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public List<String> letterCombinations(String digits) {
        String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<String>();
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            String str = dict[digits.charAt(i) - '0'];
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < str.length(); j++) {
                for (int k = 0; k < result.size(); k++) {
                    temp.add(result.get(k) + str.substring(j, j + 1));
                }
            }
            result = temp;
        }
        return result;
    }

    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinations2(digits, "", dict, result);
        return result;
    }

    public void letterCombinations2(String digits, String cur, String[] dict, List<String> result) {
        if (digits.length() == 0) {
            result.add(cur);
            return;
        }
        String str = dict[digits.charAt(0) - '0'];
        for (int i = 0; i < str.length(); i++) {
            letterCombinations2(digits.substring(1), cur + str.substring(i, i + 1), dict, result);
        }
    }


    public List<List<Integer>> combination4(int[] nums, int target) {
        if (target <= 0) return null;
        List<List<Integer>> result = new ArrayList<>();
        combinationSum4(nums, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void combinationSum4(int[] candidates, int target, int index, List<Integer> oneResult, List<List<Integer>> result) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<Integer>(oneResult));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            oneResult.add(candidates[i]);
            combinationSum4(candidates, target - candidates[i], 0, oneResult, result);
            oneResult.remove(oneResult.size() - 1);
        }
    }

    public int combination4(int[] nums, int target, int index) {
        if (index < 0) return 0;
        if (target < 0) return 0;
        if (target == 0) return 1;
        return combination4(nums, target - nums[index], index)
                + combination4(nums, target, index - 1);

    }

    public List<List<Integer>> factorCombination(int x) {
        List<List<Integer>> result = new ArrayList<>();
        factorCombination(x, 2, 1, new ArrayList<Integer>(), result);
        return result;
    }

    public void factorCombination(int x, int start, int curProduct, List<Integer> oneResult, List<List<Integer>> result) {
        if (curProduct > x) return;
        if (curProduct == x) {
            result.add(new ArrayList<Integer>(oneResult));
            return;
        }
        for (int i = start; i < x; i++) {
            if (x % i == 0) {
                oneResult.add(i);
                factorCombination(x, i, curProduct * i, oneResult, result);
                oneResult.remove(oneResult.size() - 1);
            }
        }
    }

    public boolean hasPath(char[][] matrix, int[] target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix[target[0]][target[1]] == 'k') return false;

        return hasPath(matrix, target, 0, 0, new boolean[m][n]);
    }

    public boolean hasPath(char[][] matrix, int[] target, int x, int y, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) return false;


        if ((x > 0 && matrix[x - 1][y] == 'k')
                || (x < m - 1 && matrix[x + 1][y] == 'k')
                || (y > 0 && matrix[x][y - 1] == 'k')
                || (y < n - 1 && matrix[x][y + 1] == 'k')
                || (x > 0 && y > 0 && matrix[x - 1][y - 1] == 'k')
                || (x > 0 && y < n - 1 && matrix[x - 1][y + 1] == 'k')
                || (x < m - 1 && y > 0 && matrix[x + 1][y - 1] == 'k')
                || (x < m - 1 && y < n - 1 && matrix[x + 1][y + 1] == 'k')) {
            return false;
        }

        if (x == target[0] && y == target[1]) {
            return true;
        }

        visited[x][y] = true;
        matrix[x][y] = '1';
        if (hasPath(matrix, target, x + 1, y, visited)) {
            return true;
        } else if (hasPath(matrix, target, x, y + 1, visited)) {
            return true;
        } else if (hasPath(matrix, target, x - 1, y, visited)) {
            return true;
        } else if (hasPath(matrix, target, x, y - 1, visited)) {
            return true;
        }
        return false;
    }

//    public int majorityElement(int[] nums) {
//        int result = nums[0], count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (count == 0) {
//                count = 1;
//                result = nums[i];
//            } else if (result == nums[i]) {
//                count++;
//            } else {
//                count--;
//            }
//        }
//        return result;
//    }

    public List<Integer> majorityElement(int[] nums, int n) {
        List<Integer> results = new ArrayList<>();
        int[] candidates = new int[n], count = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (candidates[0] == nums[i]) {
                count[0]++;
            } else if (candidates[1] == nums[i]) {
                count[1]++;
            } else if (count[0] == 0) {
                count[0] = 1;
                candidates[0] = nums[i];
            } else if (count[1] == 0) {
                count[1] = 1;
                candidates[1] = nums[i];
            } else {
                count[0]--;
                count[1]--;
            }
        }
        for (int candidate : candidates) {
            int c = 0;
            for (int num : nums) {
                if (num == candidate) {
                    c++;
                }
            }
            if (c > nums.length / 3) {
                results.add(candidate);
            }
        }
        return results;
    }

    public List<Integer> findPrimes(int n) {
        List<Integer> results = new ArrayList<>();
        boolean[] A = new boolean[n + 1];
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!A[i]) {
                for (int j = i * i, count = 0; j <= n; count++, j = i * i + count * i) {
                    A[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!A[i])
                results.add(i);
        }
        return results;
    }

    public List<Integer> findFactors(int n) {
        List<Integer> results = new ArrayList<>();
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                results.add(i);
                if (n / i != i) {
                    results.add(n / i);
                }
            }
        }
        return results;
    }

    static int count = 0;
    static int result = 0;

    public int kthSmallest(TreeNode root, int k) {

        inorder(root, k);
        return result;
    }

    public void inorder(TreeNode node, int k) {
        if (node == null || count >= k) return;
        inorder(node.left, k);
        count++;
        if (k == count) {
            result = node.val;
            return;
        }
        //System.out.println(node.val);
        inorder(node.right, k);
    }

    public List<List<Integer>> allRootToLeafSum(TreeNode node, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        allRootToLeafSum(node, sum, new ArrayList<Integer>(), result);
        return result;
    }

    public void allRootToLeafSum(TreeNode node, int sum, List<Integer> oneResult, List<List<Integer>> result) {
        if (node == null) return;
        sum -= node.val;
        List<Integer> newOneResult = new ArrayList<>(oneResult);
        newOneResult.add(node.val);
        if (sum < 0) return;
        if (sum == 0) {
            result.add(new ArrayList<Integer>(newOneResult));
            return;
        }
        allRootToLeafSum(node.left, sum, newOneResult, result);
        allRootToLeafSum(node.right, sum, newOneResult, result);

    }

    public boolean hasRootToLeafSum(TreeNode node, int sum) {
        if (sum == 0) return true;
        if (node == null) return false;
        return hasRootToLeafSum(node.left, sum - node.val) || hasRootToLeafSum(node.right, sum - node.val);
    }

    public void allPaths(TreeNode node, int curPath) {
        if (node == null) return;
        curPath = curPath * 10 + node.val;
        if (node.left == null && node.right == null) {
            System.out.println(curPath);
            return;
        }
        allPaths(node.left, curPath);
        allPaths(node.right, curPath);
    }

    public int allPathsSum(TreeNode node, int curSum) {
        if (node == null) return 0;
        curSum = curSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return curSum;
        }
        return allPathsSum(node.left, curSum) + allPathsSum(node.right, curSum);
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        for (int i = 0; i < s2.length(); i++) {
            if (checkSame(s1, s2.substring(i, s1.length()))) return true;
        }
        return false;
    }

    public boolean checkSame(String s1, String s2) {
        int[] set1 = new int[128];
        int[] set2 = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            set1[s1.charAt(i)]++;
            set2[s2.charAt(i)]++;
        }
        for (int i = 0; i < 128; i++) {
            if (set1[i] != set2[i]) return false;
        }
        return true;
    }

    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.max(left, right) + 1;
    }

    public int maxPathSum(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathSum(node.left));
        int right = Math.max(0, maxPathSum(node.right));
        result = Math.max(result, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public int numOfIslands(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int count = 0;
        boolean[][] visited = new boolean[height][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    bfs(matrix, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] matrix, int i, int j, boolean[][] visited) {
        int height = matrix.length;
        int width = matrix[0].length;
        if (i < 0 || i >= height || j < 0 || j >= width || visited[i][j] || matrix[i][j] == 0) return;
        visited[i][j] = true;
        dfs(matrix, i + 1, j, visited);
        dfs(matrix, i - 1, j, visited);
        dfs(matrix, i, j + 1, visited);
        dfs(matrix, i, j - 1, visited);

    }

    public void bfs(int[][] matrix, int i, int j, boolean[][] visited) {
        int height = matrix.length;
        int width = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int x = index[0];
            int y = index[1];
            if (x < 0 || x >= height || y < 0 || y >= width || visited[x][y] || matrix[x][y] == 0) continue;
            visited[x][y] = true;
            queue.add(new int[]{x + 1, y});
            queue.add(new int[]{x - 1, y});
            queue.add(new int[]{x, y + 1});
            queue.add(new int[]{x, y - 1});
        }

    }

    public int allPaths(TreeNode root, String path, int sum) {
        if (root == null) return 0;
        return allPaths(root, path, 0, sum)
                + allPaths(root.left, path, sum)
                + allPaths(root.right, path, sum);

    }

    public int allPaths(TreeNode root, String path, int curSum, int sum) {
        int result = 0;
        if (root == null) return result;
        String newPath = path + " " + root.val;
        curSum += root.val;
        if (sum == curSum) {
            System.out.println(newPath);
            result += 1;
        }
        return result + allPaths(root.left, newPath, curSum, sum) + allPaths(root.right, newPath, curSum, sum);

    }

    public List<List<Pair<String, String>>> findPaths(String[] strings, String start, String end) {
        // initialize adjList
        Map<String, List<Pair<String, String>>> adjList = new HashMap<>();

        for (String str: strings) {
            String[] tuple = str.split(",");
            if (adjList.containsKey(tuple[0])) adjList.get(tuple[0]).add(new Pair<String, String>(tuple[0], tuple[1]));
            else {
                List<Pair<String, String>> list = new ArrayList<>();
                list.add(new Pair<String, String>(tuple[0], tuple[1]));
                adjList.put(tuple[0], new ArrayList<>(list));
            }
        }
        if (!adjList.containsKey(start)) return null;

        List<List<Pair<String, String>>> result = new ArrayList<>();
        List<Pair<String, String>> oneResult = new ArrayList<>();
        Queue<Pair<String, List<Pair<String, String>>>> q = new LinkedList<>();
        q.add(new Pair<String, List<Pair<String, String>>>(start, oneResult));
        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Pair<String, List<Pair<String, String>>> vtex = q.poll();
            if (!adjList.containsKey(vtex.getKey()) || visited.contains(vtex.getKey())) continue;
            visited.add(vtex.getKey());
            List<Pair<String, String>> list = adjList.get(vtex.getKey());
            for (Pair<String, String> str: list) {
                List<Pair<String, String>> newResult =  new ArrayList<Pair<String, String>>(vtex.getValue());
                newResult.add(str);
                if (str.getValue().equals(end)) {
                    result.add(newResult);
                } else {
                    q.add(new Pair<String, List<Pair<String, String>>>(str.getValue(), newResult));
                }
            }
        }
        return result;
    }

    public int calculate(String[] strings, String start, String end) {
        // initialize adjList
        Map<String, List<Pair<String, Integer>>> adjList = new HashMap<>();

        for (String str: strings) {
            String[] tuple = str.split(",");
            if (adjList.containsKey(tuple[0])) adjList.get(tuple[0]).add(new Pair<>(tuple[1], Integer.parseInt(tuple[2])));
            else {
                List<Pair<String, Integer>> list = new ArrayList<>();
                list.add(new Pair<>(tuple[1], Integer.parseInt(tuple[2])));
                adjList.put(tuple[0], new ArrayList<>(list));
            }
        }
        if (!adjList.containsKey(start)) throw new RuntimeException("no result");

        int curResult = 1;
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(start, curResult));
        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Pair<String, Integer> vtex = q.poll();
            if (!adjList.containsKey(vtex.getKey()) || visited.contains(vtex.getKey())) continue;
            visited.add(vtex.getKey());
            List<Pair<String, Integer>> list = adjList.get(vtex.getKey());
            for (Pair<String, Integer> str: list) {
                if (str.getKey().equals(end)) {
                    return vtex.getValue() * str.getValue();
                } else {
                    q.add(new Pair<>(str.getKey(), vtex.getValue() * str.getValue()));
                }
            }
        }
        return -1;
    }

    /**
     *
     * 1,2,3
     * 4,5,6
     * 7,8,9
     *
     *
     * @param grid
     * @return
     */

    public List<String> getAllPaths(int[][] grid) {
        List<String> result = new ArrayList<>();
        Stack<Pair<int[], String>> stack = new Stack<>();
        stack.push(new Pair<int[], String>(new int[]{0, 0}, ""));
        while (!stack.isEmpty()) {
            Pair<int[], String> vtex = stack.pop();
            int[] indexes = vtex.getKey();
            int i = indexes[0], j = indexes[1];
            if (i == grid.length - 1 && j == grid.length - 1) {
                result.add((vtex.getValue() + " -> " + grid[i][j]).replaceFirst(" -> ", ""));
                continue;
            }
            if (i == grid.length || j == grid.length) continue;
            stack.push(new Pair<int[], String>(new int[]{i + 1, j}, vtex.getValue() +  " -> " + grid[i][j]));
            stack.push(new Pair<int[], String>(new int[]{i, j + 1}, vtex.getValue() + " -> " + grid[i][j]));
        }
        return result;
    }

    public int missingBrackets(String str) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') stack.push('(');
            else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    count++;
                }
            }
        }
        return count + stack.size();
    }
}
