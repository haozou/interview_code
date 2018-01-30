package com.hao.interview.concurrency;

/**
 * Created by hzou on 11/4/16.
 */
public class Producer implements Runnable {
    private Broker broker = null;
    private String name = null;

    public Producer(String name, Broker broker) {
        this.name = name;
        this.broker = broker;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 10) {
                this.broker.push(i);
                System.out.println("producer " + name + " produced " + i++ + " into the broker");
                Thread.sleep(1000);
            }
            this.broker.push(-1);
            System.out.println("producer finished job");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
