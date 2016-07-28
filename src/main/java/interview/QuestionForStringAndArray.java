package interview;

import com.sun.tools.javac.util.*;

import java.util.*;
import java.util.List;

/**
 * Created by Hao on 2/19/16.
 */
public class QuestionForStringAndArray {
    public QuestionForStringAndArray() {
    }

    /**
     * 1.1 Implement an algorithm to determine if a string has all unique characters
     * What if you can not use additional data structures?
     * Time Complexity = O(n)
     * @param str
     * @return
     */
    public boolean isUnique(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (set.contains(c)) {
                return false;
            } else {
                set.add(str.charAt(i));
            }
        }
        return true;
    }

    public boolean isUnique2(String str) {
        int checker = 0;
        for (int i = 0; i< str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    /**
     * 1.2 Write code to reverse a C-Style String
     * (C-String means that “abcd” is represented as five characters, including the null character )
     * @param strs
     */
    public void reverseArray(char[] strs) {
        for (int i = 0, j = strs.length - 1; i < j; i++, j--) {
            char tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
    }

    /**
     * 1.3 Design an algorithm and write code to remove the duplicate characters in a string without using any additional buffer
     * NOTE: One or two additional variables are fine An extra copy of the array is not
     * FOLLOW UP
     * Write the test cases for this method
     * @param strs
     */
    public void removeDuplicates(char[] strs) {
        if (strs == null || strs.length <= 1) return;

        int i, j, tail = 1;
        for (i = 1; i < strs.length; i++) {
            for (j = 0; j < tail; j++) {
                if (strs[i] == strs[j]) {
                    break;
                }
            }
            if (tail == j) {
                strs[tail++] = strs[i];
            }
        }
        while (tail < strs.length) {
            strs[tail++] = 0;
        }
    }

    public void removeDuplicates2(char[] strs) {
        if (strs == null || strs.length <= 1) return;

        Map<Character, Boolean> set = new HashMap<>();

        int tail = 1;
        set.put(strs[0], true);
        for (int i = 1; i < strs.length; i++) {
            if (!set.containsKey(strs[i])) {
                set.put(strs[i], true);
                strs[tail++] = strs[i];
            }
        }
        while (tail < strs.length) {
            strs[tail++] = 0;
        }
    }

    /**
     *1.4 Write a method to decide if two strings are anagrams or not
     */
    public boolean isAnagram(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        int numUniqueChars = 0;
        int numCompleteChars = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (map.containsKey(c)) {
                int newCount = map.get(c) + 1;
                map.put(c, newCount);
            } else {
                map.put(c, 1);
                numUniqueChars++;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            int newCount = map.get(c) - 1;
            map.put(c, newCount);

            if (map.get(c) == 0) {
                numCompleteChars++;
                if (numCompleteChars == numUniqueChars) {
                    return i == b.length() - 1;
                }
            }
        }
        return false;
    }
    /**
     * 1.5 Write a method to replace all spaces in a string with ‘%20’
     */
    public char[] replaceFun(char[] strs) {
        if (strs == null || strs.length == 0) return strs;
        int len = strs.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (strs[i] == ' ') {
                count++;
            }
        }
        if (count == 0) return strs;
        int newLen = len + count * 2;
        char[] copys = new char[newLen];
        for (int i = len - 1; i >= 0; i--) {
            if (strs[i] == ' ') {
                copys[newLen - 1] = '0';
                copys[newLen - 2] = '2';
                copys[newLen - 3] = '%';
                newLen -= 3;
            } else {
                copys[newLen - 1] = strs[i];
                newLen--;
            }
        }
        return copys;
    }

    /**
     *1.6 Given an image represented by an NxN matrix,
     *where each pixel in the image is 4 bytes,
     *write a method to rotate the image by 90 degrees
     *Can you do this in place?
     */
    public void rotateImage(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {

            int last = n - 1 - i;
            for (int j = i; j < last; j++) {
                int offset = j - i;
                int top = matrix[i][j];
                matrix[i][j] = matrix[last - offset][i];
                matrix[last - offset][i] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[j][last];
                matrix[j][last] = top;
            }
        }
    }

    /**
     *1.7 Write an algorithm such that if an element in an MxN matrix is 0,
     *its entire row and column is set to 0
     */
    public void setZeros(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] row = new int[n];
        int[] column = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    column[j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == 1 || column[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    /**
     *1.8 Assume you have a method isSubstring which checks
     *if one word is a substring of another Given two
     *strings,s1 and s2, write code to check if s2 is a rotation of s1 using
     *only one call to isSubstring (i e , “waterbottle” is a rotation of “erbottlewat”)
     */
    public boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        String concatenated = s2 + s2;
        return concatenated.contains(s1);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (start - 1 > i && nums[start] == nums[start - 1]) {
                    start++;
                    continue;
                }
                if (end + 1 < nums.length && nums[end] == nums[end + 1]) {
                    end--;
                    continue;
                }
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    List<Integer> level = new ArrayList<>();
                    level.add(nums[i]);
                    level.add(nums[start]);
                    level.add(nums[end]);
                    result.add(level);
                    start++;
                    end--;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 0, 4, target);
    }

    public List<List<Integer>> nSum(int[] nums, int idx, int n, int target) {
        if (n == 2) return twoSum(nums, idx, target);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = idx; i < nums.length - n + 1;i++) {
            // if (i - 1 >= idx && nums[i - 1] == nums[i]) {
            //     i++;
            //     continue;
            // }
            List<List<Integer>> temp = nSum(nums, i + 1, n - 1, target);
            for (List<Integer> t: temp) {
                t.add(0, nums[i]);
            }
            result.addAll(temp);
            int t = nums[i];
            while (i <= nums.length - n && nums[i] == t) {
                i++;
            }
            i--;
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int idx, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= idx) return result;
        for (int i = idx, j = nums.length - 1; i < j; ) {
            if (i - 1 >= idx && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            if (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                j--;
                continue;
            }
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                List<Integer> level = new ArrayList<Integer>();
                level.add(nums[i]);
                level.add(nums[j]);
                result.add(level);
                i++;
                j--;
            }
        }
        return result;
    }

    public int[] radixSort(int[] nums) {
        if (nums == null) return nums;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        for (int i = 1; max / i > 0; i = i * 10) {
            countSort(nums, i);
        }
        return nums;
    }

    public void countSort(int[] nums, int exp) {
        int[] result = new int[nums.length];
        int[] counts = new int[10];
        for (int i = 0; i < nums.length; i++) {
            counts[(nums[i]/exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[counts[(nums[i]/exp) % 10] - 1] = nums[i];
            counts[(nums[i]/exp) % 10]--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
    }

    public String countAndSay(int n) {
        if (n < 1) return "";
        StringBuilder builder = new StringBuilder();
        builder.append("1");
        for (int i = 2; i < n; i++) {
            String cur = builder.toString();
            builder.setLength(0);
            int count = 1;
            for (int j = 0; j < cur.length(); j++) {
                if (j + 1 < cur.length() && cur.charAt(j) == cur.charAt(j+1)) {
                    count++;
                } else {
                    builder.append(count).append(cur.charAt(j));
                    count = 1;
                }
            }
        }
        return builder.toString();
    }

    public int footballProblem(int[] nums, int target, int index) {
        if (index < 0) return 0;
        if (target == 0) return 1;
        if (target < 0) return 0;
        return footballProblem(nums, target - nums[index], index)
                + footballProblem(nums, target, index - 1);

    }

    private boolean matches(int[] sourceDict, int[] targetDict, int lens) {
        int matches = 0;
        for (int i = 0; i < 256; i++) {
            if (sourceDict[i] > 0 && targetDict[i] > 0) {
                matches++;
            }
        }
        return matches == lens;
    }
    public String minLenSubStringWithAllChars(String source, String target) {
        int[] sourceDict = new int[256];
        int[] targetDict = new int[256];
        int minLen = Integer.MAX_VALUE;
        int bestStart = -1, bestEnd = -1;
        for (int i = 0; i < target.length(); i++) {
            sourceDict[source.charAt(i)]++;
            targetDict[target.charAt(i)]++;
        }
        for (int start = 0, end = target.length();
             start < source.length(); ) {
            if (!matches(sourceDict, targetDict, target.length()) && end < source.length()) {
                sourceDict[source.charAt(end)]++;
                end++;
            } else if (matches(sourceDict, targetDict, target.length())){
                if (end - start < minLen) {
                    minLen = end - start;
                    bestStart = start;
                    bestEnd = end;
                }
                sourceDict[source.charAt(start)]--;
                start++;
            } else {
                break;
            }
        }
        if (bestStart == -1) {
            return null;
        }
        return source.substring(bestStart, bestEnd);
    }

    public void printAllsubsets(String str) {
        printAllsubsets(str, 0, "");
    }

    public void printAllsubsets(String str, int i, String subset) {
        if (i == str.length()) {
            System.out.println(subset);
        } else {
            printAllsubsets(str, i + 1, subset);
            subset += str.charAt(i);
            printAllsubsets(str, i + 1, subset);
        }
    }

    public void printAllproducts(int[] nums) {
        printAllproducts(nums, 0, 1);
    }

    public void printAllproducts(int[] nums, int i, int product) {
        if (i == nums.length) {
            System.out.println(product);
        } else {
            printAllproducts(nums, i + 1, product);
            product *= nums[i];
            printAllproducts(nums, i + 1, product);
        }
    }


    public void combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        generate(candidates, target, 0, "");
    }

    private void generate(int[] candidates, int target, int start, String one) {
        if (target < 0) return;
        if (target == 0) {
            System.out.println(one);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            one += Integer.toString(candidates[i]) + " ";
            generate(candidates, target - candidates[i], i, one);
            one = one.substring(0, one.length() - Integer.toString(candidates[i]).length() - 1);
        }
    }

    public void combine(int n, int k) {
        combine(n, k, 1, "");
    }

    private void combine(int n, int k, int start, String path) {
        if (k == 0) {
            System.out.println(path);
            return;
        }
        for (int i = start; i <= n; i++) {
            String newPath = path + Integer.toString(i) + " ";
            combine(n, k - 1, i + 1, newPath);
            //path = path.substring(0, path.length() - Integer.toString(i).length() - 1);
        }
    }

    public String generateRandomString() {

    }
}


