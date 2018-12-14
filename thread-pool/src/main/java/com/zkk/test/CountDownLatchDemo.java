package com.zkk.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 一个线程往集合中添加10个数据，当添加到5个时，开启另一个线程
 */
public class CountDownLatchDemo {
    private static volatile int size = 0;
    public static void main(String[] args) {
        //UseCountlatch();
    }


    private static void UseCountlatch() {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newCachedThreadPool();
        int i = 0;
        List<Integer> list = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (list.size() != 5) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程3启动");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("数据添加开始");
                for (int i = 1;i<=10;i++) {
                    list.add(i);
                    System.out.println(i);
                    latch.countDown();
                }
            }
        }).start();
    }
}
