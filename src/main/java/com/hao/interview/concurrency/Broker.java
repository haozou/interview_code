package com.hao.interview.concurrency;

/**
 * Created by hzou on 11/4/16.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker {
    private ArrayBlockingQueue<Integer> queue;
    private Boolean continueProducing = Boolean.TRUE;
    private final int DEFAULT_SIZE = 10;

    public Broker() {
        queue = new ArrayBlockingQueue<Integer>(DEFAULT_SIZE);
    }

    public Broker(int size) {
        queue = new ArrayBlockingQueue<Integer>(size);
    }
    public void push(Integer data) throws InterruptedException {
        this.queue.put(data);
    }

    public Integer pop() throws InterruptedException {
        return this.queue.poll(1, TimeUnit.SECONDS);
    }
}
