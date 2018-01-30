package com.hao.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by hzou on 1/29/18.
 */
public class Microsoft {
    public boolean isBST(QuestionForTree.TreeNode node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    public boolean isBST(QuestionForTree.TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val < min || node.val > max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    public String removeDupCharInplace(char[] str) {

        int count = 0;
        for (int i = 0, j = 0; i < str.length; i++) {
            for (j = 0; j < count; j++) {
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == count) {
                str[count++] = str[i];
            }
        }
        return String.copyValueOf(str, 0, count);
    }

    public int binarySearch(int[] nums, int target) {
        for (int left = 0, right = nums.length - 1; left <= right;) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                if (nums[left] > nums[mid] && target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else if (target < nums[mid]) {
                if (nums[right] < nums[mid] && target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            } else {
                return mid;
            }
        }
        return -1;
    }

    public QuestionForLinkedList.LinkedNode addHead(QuestionForLinkedList.LinkedNode head, QuestionForLinkedList.LinkedNode node) {
        if (head == null) return node;
        if (node == null) return head;
        node.next = head;
        head = node;
        return head;
    }

    public QuestionForLinkedList.LinkedNode addTwoList(QuestionForLinkedList.LinkedNode l1, QuestionForLinkedList.LinkedNode l2) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                s1.push(l1.val);
                l1 = l1.next;
            }
            if (l2  != null) {
                s2.push(l2.val);
                l2 = l2.next;
            }
        }
        QuestionForLinkedList.LinkedNode head  = new QuestionForLinkedList.LinkedNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int x = 0, y = 0;
            if (!s1.isEmpty()) {
                x = s1.pop();
            }
            if (!s2.isEmpty()) {
                y = s2.pop();
            }
            int sum = x + y + head.val;
            head.val = sum % 10;
            if (!s1.isEmpty() || !s2.isEmpty() || sum > 9) {
                QuestionForLinkedList.LinkedNode newNode = new QuestionForLinkedList.LinkedNode(sum / 10);
                head = addHead(head, newNode);
            }

        }
        return head;
    }

    public String lengthEncoding(String str) {

        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
                continue;
            }
            builder.append(str.charAt(i - 1));
            builder.append(count);
            count = 1;
        }
        builder.append(str.charAt(str.length() - 1));
        builder.append(count);
        return builder.toString();
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restoreIpAddresses(s, 0, "", result, 0);
        return result;
    }

    public void restoreIpAddresses(String s, int start, String oneResult, List<String> result, int count) {
        if (count > 4) return;
        if (count == 4 && start == s.length()) {
            result.add(oneResult.substring(0, oneResult.length() - 1));
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (start + i > s.length()) break;
            String str = s.substring(start, start + i);
            if ((str.startsWith("0") && str.length() > 1) || (i == 3 && Integer.parseInt(str) >= 256)) continue;
            restoreIpAddresses(s, start + i, oneResult + str + ".", result, count + 1);
        }
    }

    public void drawCircle(double r) {
        for (double x = -r; x <= r; x++) {
            for (double y = -r; y <= r; y++) {
                if (Math.sqrt(x*x + y*y) <= r) System.out.print(".");
                else System.out.print(" ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
