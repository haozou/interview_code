package com.hao.interview.concurrency;

/**
 * Created by hzou on 11/4/16.
 */
public class Producer implements Runnable{
    private Broker broker = null;

    public Producer(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 5) {
                this.broker.push(i);
                System.out.println("producer produced " + i++ + " into the broker");
                Thread.sleep(100);
            }
            System.out.println("producer finished job");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
