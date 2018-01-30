package com.hao.interview.concurrency;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzou on 1/25/18.
 */
public class ConsumerTest {
    @Test
    public void testBroker() {
        Broker<Integer> broker = new Broker<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        Thread producer = new Thread(new Producer("producer", broker));
        Thread consumer = new Thread(new Consumer("consumer", broker));
        service.submit(producer);
        service.submit(consumer);

        try {
            service.awaitTermination(100, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
