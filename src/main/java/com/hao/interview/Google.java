package com.hao.interview;

import com.hao.interview.QuestionForTree.TreeNode;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by hzou on 10/2/16.
 */
public class Google<T> {
    public Google(){}

    public boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        for (; i < a.length() && j < b.length();) {
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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'G') {
                    bfs(matrix, i, j);
                    return;
                }
            }
        }

    }

    public void bfs(char[][] matrix, int x, int y) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            x = element[0];
            y = element[1];
            int step = element[2];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == 'B' || visited[x][y]) continue;
            char c = matrix[x][y];
            if (c != 'G' ) {
                if (c == '0' || c - '0' > step) {
                    matrix[x][y] = Character.forDigit(step, 10);
                } else {
                    continue;
                }
            } else {
                visited[x][y] = true;
                step = 0;
            }
            queue.add(new int[]{x+1, y, step + 1});
            queue.add(new int[]{x, y+1, step + 1});
            queue.add(new int[]{x-1, y, step + 1});
            queue.add(new int[]{x, y-1, step + 1});
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

    public List<List<Integer>> permutation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutation(nums, new ArrayList<Integer>(), result);
        return result;
    }

    public void permutation(int[] nums, List<Integer> oneResult, List<List<Integer>> result) {
        if (oneResult.size() == nums.length) {
            result.add(new ArrayList<Integer>(oneResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (oneResult.contains(nums[i])) continue;
            oneResult.add(nums[i]);
            permutation(nums, oneResult, result);
            oneResult.remove(oneResult.size() - 1);
        }
    }

    public void nextPermute(Integer[] nums) {
        int m = -1, n = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i-1]) {
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
        factorCombination(x, 2, 1,  new ArrayList<Integer>(), result);
        return result;
    }

    public void factorCombination(int x, int start, int curProduct , List<Integer> oneResult, List<List<Integer>> result) {
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
        if (matrix[target[0]][target[1]] == 'k' ) return false;

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
            } else if (count[1] == 0){
                count[1] = 1;
                candidates[1] = nums[i];
            } else {
                count[0]--;
                count[1]--;
            }
        }
        for (int candidate: candidates) {
            int c = 0;
            for (int num: nums) {
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
                for (int j = i * i, count = 0; j <= n; count++, j = i *i + count * i) {
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
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
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


}
