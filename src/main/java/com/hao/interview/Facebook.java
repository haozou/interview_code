package com.hao.interview;
import com.hao.interview.QuestionForTree.TreeNode;
import com.hao.interview.QuestionForLinkedList.LinkedNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hzou on 3/15/17.
 */
public class Facebook {

    static class Helper {
        TreeNode head;
        TreeNode prev;
        TreeNode tail;
    }
    public Facebook(){}


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
        int count = (int)Math.pow(2.0, nums.length);
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
        if(index > nums.length) return;
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
        while(cur != null) {
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
            for (List<Integer> one: tempResult) {
                one.add(0 , nums[i]);
            }
            result.addAll(tempResult);
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start, j = nums.length - 1; i < j;) {
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
            } else if (sum > target){
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
}
