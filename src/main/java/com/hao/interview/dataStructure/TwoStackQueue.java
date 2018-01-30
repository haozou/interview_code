package com.hao.interview.dataStructure;

import java.util.Stack;

/**
 * Created by hzou on 1/23/18.
 */
public class TwoStackQueue<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;

    public TwoStackQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void enqueue(T e) {
        this.stack1.push(e);
    }

    public void enqueue2(T e) {

        while (!stack1.isEmpty())
            this.stack2.push(stack1.pop());
        stack1.push(e);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public T dequeue2() {
        return stack1.pop();
    }

    public boolean isEmpty2() {
        return stack2.empty();
    }

    public T dequeue() {
        if (!stack2.isEmpty()) return stack2.pop();
        while (!this.stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
       return stack2.isEmpty() && stack1.isEmpty();
    }
}
