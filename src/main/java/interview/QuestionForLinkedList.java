package interview;

import sun.awt.image.ImageWatched;

import javax.sound.sampled.Line;
import java.util.HashSet;

/**
 * Created by Hao on 2/23/16.
 */
public class QuestionForLinkedList {
    static class LinkedNode {
        int val;
        LinkedNode next;
        public LinkedNode(int val){this.val = val;}
    }

    public QuestionForLinkedList() {
    }



    public LinkedNode reverse(LinkedNode node) {
        if (node == null) return node;
        LinkedNode cur = node;
        LinkedNode head = null;
        while (cur != null) {
            LinkedNode next = cur.next;
            cur.next = head;
            head = cur;
            cur = next;
        }
        return head;
    }

    public LinkedNode findMiddle(LinkedNode node) {
        if (node == null) return node;
        LinkedNode chaser = node;
        LinkedNode runner = node;

        while (runner.next != null) {
            runner = runner.next.next;
            if (runner == null) {
                return chaser;
            }
            chaser = chaser.next;
        }
        return chaser;
    }

    /**
     * 2.1 Write code to remove duplicates from an unsorted linked list FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed?
     * @param node
     */
    public void removeDuplicate(LinkedNode node) {
        HashSet<Integer> visited = new HashSet<>();
        LinkedNode cur = node;
        LinkedNode pre = null;
        while (cur != null) {
            if (visited.contains(cur.val)) {
                LinkedNode tmp = cur;
                pre.next = tmp.next;
                tmp = null;
            } else {
                visited.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public void removeDuplicate2(LinkedNode node) {
        LinkedNode cur = node;
        LinkedNode pre = null;
        while (cur != null) {
            LinkedNode runner = node;
            while (runner != cur) {
                if (runner.val == cur.val) {
                    LinkedNode tmp = cur;
                    pre.next = tmp.next;
                    tmp = null;
                    break;
                }
                runner = runner.next;
            }
            if (runner == cur) {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    /**
     * 2.2 Implement an algorithm to find the nth to last element of a singly linked list
     * @param node
     * @return
     */
    public LinkedNode nthToLast(LinkedNode node, int n) {
        if (node == null) return null;
        LinkedNode n1 = node;
        LinkedNode n2 = node;

        for (int i = 0; i < n; i++) {
            if (n2 == null) {
                return null;
            }
            n2 = n2.next;
        }
        while (n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 2.3 Implement an algorithm to delete a node in the middle of a single linked list,
     * given only access to that node
     * EXAMPLE
     * Input: the node ‘c’ from the linked list a->b->c->d->e
     * Result: nothing is returned, but the new linked list looks like a->b->d->e
     * @param node
     */
    public void deleteSingleNode(LinkedNode node) {
        if (node == null) return;
        if (node.next == null) throw new RuntimeException("the input node is not in the middle of the linked list");
        LinkedNode next = node.next;
        node.val = next.val;
        node.next = next.next;
        next = null;
    }

    /**
     * 2.4 You have two numbers represented by a linked list, where each node contains a single digit
     * The digits are stored in reverse order, such that the 1’s digit is at the head of the list
     * Write a function that adds the two numbers and returns the sum as a linked list
     *
     * EXAMPLE
     * Input: (3 -> 1 -> 5), (5 -> 9 -> 2) Output: 8 -> 0 -> 8
     * @param node1
     * @param node2
     * @return
     */
    public LinkedNode addLists(LinkedNode node1, LinkedNode node2) {
        LinkedNode result = new LinkedNode(0);
        LinkedNode head = result;
        while (node1 != null || node2 != null) {
            int val1 = 0, val2 = 0;
            if (node1 != null) {
                val1 = node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                val2 = node2.val;
                node2 = node2.next;
            }
            int sum = val1 + val2 + result.val;
            result.val = sum % 10;
            if (node1 != null || node2 != null || sum / 10 > 0) {
                result.next = new LinkedNode(sum / 10);
                result = result.next;
            }
        }
        return head;
    }

    /**
     * 2.5 Given a circular linked list, implement an algorithm which returns node at the beginning of the loop
     * DEFINITION
     * Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an earlier node,
     * so as to make a loop in the linked list
     * EXAMPLE
     * Input: A -> B -> C -> D -> E -> C [the same C as earlier] Output: C
     * @param node
     * @return
     */
    public LinkedNode detectCircle(LinkedNode node) {
        if (node == null) return null;
        LinkedNode chaser = node;
        LinkedNode runner = node;
        while (runner != null && runner.next != null) {
            runner = runner.next.next;
            chaser = chaser.next;
            if (runner == chaser) {
                break;
            }
        }
        if (runner == null || runner.next == null) return null;
        chaser = node;
        while (chaser != runner) {
            chaser = chaser.next;
            runner = runner.next;
        }
        return chaser;
    }

    public LinkedNode swapPairs(LinkedNode head) {
        LinkedNode cur = head;
        LinkedNode pre = head;
        //ListNode next = cur.next;
        int i = 0;
        while (cur != null && cur.next != null) {
            LinkedNode next = cur.next;
            cur.next = next.next;
            next.next = cur;
            pre = cur;
            cur = cur.next;
            i++;
        }
        return head;
    }
}
