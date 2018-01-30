package com.hao.interview.concurrency;

/**
 * Created by hzou on 11/4/16.
 */

import java.util.concurrent.ArrayBlockingQueue;

public class Broker<T> {
    private ArrayBlockingQueue<T> queue;
    private Boolean continueProducing = Boolean.TRUE;
    private final int DEFAULT_SIZE = 10;

    public Broker() {
        queue = new ArrayBlockingQueue<T>(DEFAULT_SIZE);
    }

    public Broker(int size) {
        queue = new ArrayBlockingQueue<T>(size);
    }
    public void push(T data) throws InterruptedException {
        this.queue.put(data);
    }

    public T pop() throws InterruptedException {
        return this.queue.take();
    }

}
