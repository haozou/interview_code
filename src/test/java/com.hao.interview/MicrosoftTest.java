package com.hao.interview;

import org.testng.annotations.Test;

/**
 * Created by hzou on 1/29/18.
 */
public class MicrosoftTest {
    Microsoft microsoft = new Microsoft();
    @Test
    public void testRemoveDup() {
        System.out.println(microsoft.removeDupCharInplace(new char[]{'a','b','c','c','a', 'd','f','f','d'}));
    }

    @Test
    public void testBinarySearch() {
        System.out.println(microsoft.binarySearch(new int[]{1}, 1));
    }

    @Test
    public void testAddList() {
        QuestionForLinkedList.LinkedNode l1 = new QuestionForLinkedList.LinkedNode(9);
        l1.next = new QuestionForLinkedList.LinkedNode(9);
        l1.next.next = new QuestionForLinkedList.LinkedNode(9);
        QuestionForLinkedList.LinkedNode l2 = new QuestionForLinkedList.LinkedNode(1);
        l2.next = new QuestionForLinkedList.LinkedNode(8);
        QuestionForLinkedList.LinkedNode result = microsoft.addTwoList(l1, l2);
        while (result != null) {
            System.out.println(result.val + "\t");
            result = result.next;
        }
        System.out.println();
    }

    @Test
    public void testLengthEncoding() {

        System.out.println(microsoft.lengthEncoding("wwwwaaadexxxxxx"));

    }

    @Test
    public void testIPRestore() {
        System.out.println(microsoft.restoreIpAddresses("25525511135"));
    }

    @Test
    public void testDrawCircle() {
        microsoft.drawCircle(10.0);
    }
}
