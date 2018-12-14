package com.zkk.test.ThreadPool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        System.out.println("-----主线程开始-----");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, new SynchronousQueue<>());
        int i = 0;
        System.out.println(executor.getPoolSize());
        try {
            while(true) {
                if(i>9) break;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        /*try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        System.out.println(Thread.currentThread().getName());
                    }
                });
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println(executor.getPoolSize());
        System.out.println("-----main线程结束-----");
    }
}
