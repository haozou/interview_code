package com.hao.interview;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

/**
 * Created by Hao on 2/23/16.
 */
public class QuestionForTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode next;
        public TreeNode(int val) {this.val = val;}
    }

    static class Helper {
        TreeNode head;
        TreeNode prev;
        TreeNode tail;
    }
    public QuestionForTree() {
    }

    /**
     * 4.1 Implement a function to check if a tree is balanced For the purposes of this question,
     * a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
     * from the root by more than one
     * @param node
     * @return
     */
    public boolean isBalanced(TreeNode node) {
        return maxDepth(node) - minDepth(node) <= 1;
    }

    private int minDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.val);
                node = node.right;
            }
        }
        System.out.println();
    }

    public void convertBSTToDLL2(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode head = null;
        TreeNode tail = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (!stack.isEmpty()) {
                node = stack.pop();
                if (prev == null) {
                    head = node;
                } else {
                    prev.right = node;
                    node.left = prev;
                }
                prev = node;
                //System.out.print(node.val);
                node = node.right;
                if (node == null && stack.isEmpty()) {
                    tail = prev;
                }
            }
        }
        TreeNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.right;
        }
        System.out.println();
        cur = tail;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.left;
        }
        System.out.println();
    }

    public void inOrderRecursive(TreeNode node) {
        if (node == null) return;
        inOrderRecursive(node.left);
        System.out.print(node.val);
        inOrderRecursive(node.right);
    }

    /*public void convertBSTToDLL(TreeNode node) {
        Helper helper = new Helper();
        convertBSTToDLLHelper(node, helper);
        TreeNode cur = helper.head;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.right;
        }
        System.out.println();
        cur = helper.tail;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.left;
        }
        System.out.println();

    }*/

    public void convertBSTToDLLHelper(TreeNode node, Helper helper) {
        if (node == null) return;
        convertBSTToDLLHelper(node.left, helper);
        if (helper.prev == null) {
            helper.head = node;
        } else {

            helper.prev.right = node;
            node.left = helper.prev;
        }
        helper.prev = node;
        convertBSTToDLLHelper(node.right, helper);
        if (node.right == null) {
            helper.tail = node;
        }
    }

    public void convertBSTToDDLLevelOrder(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode prev = null;
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
            n.left = prev;
            n.right = queue.peek();
            prev = n;
        }
    }
    /**
     * 4.3 Given a sorted (increasing order) array,
     * write an algorithm to create a tree with minimal height
     * @param array
     * @return
     */
    public TreeNode createMinimalHeightBinaryTree(int[] array) {
        return addToTree(array, 0, array.length - 1);
    }

    private TreeNode addToTree(int[] array, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = addToTree(array, start, mid - 1);
        node.right = addToTree(array, mid + 1, end);
        return node;
    }

    public void printLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * 4.4 Given a binary search tree,
     * design an algorithm which creates a linked list of all the nodes at each depth
     * (eg, if you have a tree with depth D, you’ll have D linked lists)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        boolean flag = true;
        while (!s1.isEmpty()) {
            int size = s1.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = s1.pop();
                level.add(node.val);
                if (flag) {
                    if (node.left != null) {
                        s2.push(node.left);
                    }
                    if (node.right != null) {
                        s2.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        s2.push(node.right);
                    }
                    if (node.left != null) {
                        s2.push(node.left);
                    }
                }
            }
            Stack tmp = s1;
            s1 = s2;
            s2 = tmp;
            result.add(level);
            flag = !flag;
        }
        return result;
    }

    /**
     * 4.5 Write an algorithm to find the ‘next’ node (e g , in-order successor)
     * of a given node in a binary search tree where each node has a link to its parent
     * @param node
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode node) {
        if (node == null) return null;
        TreeNode successor = null;
        if (node.parent == null || node.right != null) {
            TreeNode leftMost = node.right;
            while (leftMost != null) {
                leftMost = leftMost.left;
            }
            successor = leftMost;
        } else {
            successor = node.parent;
            while (successor != null) {
                if (successor.left == node) {
                    break;
                }
                node = successor;
                successor = successor.parent;
            }
        }
        return successor;
    }

    /**
     * 4.6 Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree
     * Avoid storing additional nodes in a data structure.
     * NOTE: This is not necessarily a binary search tree
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode firstCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) return null;
        if (node1 == root || node2 == root) {
            return root;
        }
        TreeNode left = firstCommonAncestor(root.left, node1, node2);
        TreeNode right = firstCommonAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public TreeNode firstCommonAncestor2(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) return null;
        int c1 = 0, c2 = 0;
        TreeNode n1 = node1, n2 = node2;
        while (n1 != null || n2 != null) {
            if (n1 != null) {
                n1 = n1.parent;
                c1++;
            }
            if (n2 != null) {
                n2 = n2.parent;
                c2++;
            }
        }
        n1 = node1;
        n2 = node2;
        if (c2 > c1) {
            while (c2-- > 0) {
                n2 = n2.parent;
            }
        }  else if (c2 < c1) {
            while (c1-- > 0) {
                n1 = n1.parent;
            }
        }
        while (n1 != null && n2 != null) {
            if (n1 == n2) {
                return n1;
            } else {
                n1 = n1.parent;
                n2 = n2.parent;
            }
        }
        return null;
    }


    /**
     * 4.7 You have two very large binary trees:
     * T1, with millions of nodes, and T2, with hundreds of nodes.
     * Create an algorithm to decide if T2 is a subtree of T1
     * @param bigTree
     * @param smallTree
     * @return
     */
    public boolean isSubtree(TreeNode bigTree, TreeNode smallTree) {
        return false;
    }

    public List<List<Integer>> getAllPath(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getAllPath(root, new ArrayList<Integer>(), result);
        return result;
    }
    public void getAllPath(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(newPath);
        }
        getAllPath(node.left, newPath, result);
        getAllPath(node.right, newPath, result);
    }

    public void preOrderRecursive(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + "\t");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }

    public void preOrder(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            System.out.print(n.val + "\t");
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        System.out.println();
    }

    public void printAllPathFromRoot(TreeNode node) {
        printAllPathFromRoot(node, "");
    }

    private void printAllPathFromRoot(TreeNode node, String path) {
        if (node == null) return;
        String newPath = path + node.val + " ";
        System.out.println(newPath);
        printAllPathFromRoot(node.left, newPath);
        printAllPathFromRoot(node.right, newPath);
    }
    static class Result {
        int val = 0;
    }
    public int getMostWeightedPath(TreeNode node) {
        Result re = new Result();
        getMostWeightedPath(node, 0, re);
        return re.val;
    }

    private void getMostWeightedPath(TreeNode node, int curSum, Result re) {
        if (node == null) return;
        curSum += node.val;
        if (curSum > re.val) {
            re.val = curSum;
        }
        getMostWeightedPath(node.left, curSum, re);
        getMostWeightedPath(node.right, curSum, re);
    }

    public TreeNode convertBSTToDLL(TreeNode node) {
        Helper helper = new Helper();
        convertBSTToDLL(node, helper);
        TreeNode cur = helper.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
        cur = helper.tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.left;
        }
        System.out.println();
        return helper.head;
    }

    private void convertBSTToDLL(TreeNode node, Helper helper) {
        if (node == null) return;
        convertBSTToDLL(node.left, helper);
        if (helper.prev == null) {
            helper.head = node;
        }
        else {
            helper.prev.right = node;
            node.left = helper.prev;
        }
        helper.prev = node;
        convertBSTToDLL(node.right, helper);
        if (node.right == null) {
            helper.tail = node;
        }
    }

}
