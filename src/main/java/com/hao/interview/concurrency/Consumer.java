package com.hao.interview.concurrency;

/**
 * Created by hzou on 11/4/16.
 */
public class Consumer implements Runnable {
    private String name;
    private Broker broker;

    public Consumer(String name, Broker broker) {
        this.name = name;
        this.broker = broker;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                Object data = this.broker.pop();
                System.out.println("consumer " + name + " consumed " + data + ".");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
