package com.zkk.test.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        //默认线程回收时间60S
        ExecutorService service = Executors.newCachedThreadPool();
        int i = 0;
        try {
            while(true) {
                if(i>100) break;
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                });
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

    }
}
