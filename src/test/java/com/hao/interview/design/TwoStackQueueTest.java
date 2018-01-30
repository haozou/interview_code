package com.hao.interview.design;

import com.hao.interview.dataStructure.TwoQueueStack;
import com.hao.interview.dataStructure.TwoStackQueue;
import org.testng.annotations.Test;

/**
 * Created by hzou on 1/23/18.
 */
public class TwoStackQueueTest {
    @Test
    public void testTwoStackQueue() {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();
        queue.enqueue2(1);
        queue.enqueue2(2);
        queue.enqueue2(3);
        queue.enqueue2(4);
        queue.enqueue2(5);

        while (!queue.isEmpty())
            System.out.println(queue.dequeue2());

        //System.out.println(queue.dequeue());


        TwoQueueStack<Integer> stack = new TwoQueueStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        while (!stack.isEmpty())
            System.out.println(stack.pop());

    }

}

