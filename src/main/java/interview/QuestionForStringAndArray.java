package interview;

import java.util.*;

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
        Map<Character, Boolean> set = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (set.containsKey(c)) {
                return false;
            } else {
                set.put(str.charAt(i), true);
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
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        String concatenated = s2 + s2;
        return concatenated.contains(s1);
    }
}
