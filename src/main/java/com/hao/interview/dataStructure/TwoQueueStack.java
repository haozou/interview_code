package com.hao.interview.dataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hzou on 1/23/18.
 */
public class TwoQueueStack<T> {
    private Queue<T> q1;
    private Queue<T> q2;

    public TwoQueueStack() {
        this.q1 = new LinkedList();
        this.q2 = new LinkedList();
    }

    public void push(T e) {
        q2.add(e);
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }

        Queue<T> tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    public T pop() {
        return q1.poll();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }
}
