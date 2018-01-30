package com.hao.interview.stockOutliers.list;

import java.util.Iterator;

/**
 * Created by hzou on 10/24/17.
 */
public class ListIterator<E> implements Iterator<E> {

    private LinkedList.LinkedNode<E> node;
    public ListIterator(LinkedList.LinkedNode<E> node) {
        this.node = node;
    }
    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public E next() {
        E val = node.val;
        node = node.next;
        return val;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

}
