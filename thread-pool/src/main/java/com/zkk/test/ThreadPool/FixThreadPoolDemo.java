package com.zkk.test.ThreadPool;

import java.util.ArrayList;
import java.util.concurrent.*;

public class FixThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        //testThreadCount(service);
        testInvokeAny(service);
    }

    private static void testInvokeAny(ExecutorService service) {
        String threadName = null;
        try {
            threadName = service.invokeAny(new ArrayList<Callable<String>>() {{
                add(()->{return Thread.currentThread().getName();});
                add(()->{return Thread.currentThread().getName();});
            }});
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        System.out.println(threadName);
    }

    private static void testThreadCount(ExecutorService service) {
        int i = 0;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)service;
        System.out.println(threadPoolExecutor.getPoolSize());
        try {
            while(true) {
                if(i>10) break;
                service.execute(()->System.out.println(Thread.currentThread().getName()));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(3000);
                System.out.println(threadPoolExecutor.getPoolSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.shutdown();
        }
    }
}
