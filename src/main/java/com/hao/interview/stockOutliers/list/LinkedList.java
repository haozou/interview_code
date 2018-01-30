package com.hao.interview.stockOutliers.list;

import java.util.Iterator;

/**
 * Created by hzou on 10/24/17.
 */
public class LinkedList<E> implements List<E> {
    public static class LinkedNode<E> {
        E val;
        LinkedNode next;
        public LinkedNode(E val) {
            this.val = val;
        }
    }

    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    @Override
    public void add(E data) {
        if (head == null) {
            head = new LinkedNode(data);
            tail = head;
        } else {
            tail.next = new LinkedNode(data);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add(int pos, E data) {
        if (pos > size) throw new IndexOutOfBoundsException(String.format("Position index %d is larger than the linkedList size %d", pos, size));
        if (pos == 0) {
            LinkedNode<E> newNode = new LinkedNode(data);
            newNode.next = head;
            head = newNode;
        } else {
            LinkedNode<E> cur = head;
            while (pos-- > 0) {
                cur = cur.next;
            }
            LinkedNode<E> next = cur.next;
            cur.next = new LinkedNode<E>(data);
            cur.next.next = next;
        }
        size++;
    }

    @Override
    public void remove(int pos) {
        if (pos >= size) throw new IndexOutOfBoundsException(String.format("Position index %d is larger than the linkedList size %d", pos, size));
        if (pos == 0) {
            LinkedNode<E> newHead = head.next;
            head.next = null;
            head = newHead;
        } else {
            LinkedNode<E> cur = head;
            while (pos-- > 1) {
                cur = cur.next;
            }
            LinkedNode<E> next = cur.next;
            cur.next = next.next;
            next.next = null;
        }
        size--;
    }

    @Override
    public E get(int pos) {
        if (pos >= size) throw new IndexOutOfBoundsException(String.format("Position index %d is larger than the linkedList size %d", pos, size));
        LinkedNode<E> cur = head;
        while (pos-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(head);
    }

}
