package com.zkk.test;

import com.zkk.test.model.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        //testSumbitCallable(service);
        //testSumbitRunnable(service);
        //testExecute(service);
        //testPriority(service);
    }

    private static void testPriority(ExecutorService service) {
        Thread thread = null;
        int i = 1;
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        try {
            while(true) {
                if(i>=10) break;
                thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
                thread.setPriority(i);
                threadArrayList.add(thread);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        threadArrayList.forEach(thread1 -> thread1.start());
    }

    private static void testExecute(ExecutorService service) {
        try {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private static void testSumbitRunnable(ExecutorService service) {
        Future<?> future = null;
        try {
            future = service.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //service.shutdown();
            service.shutdownNow();
        }
        System.out.println(future.isDone());
        Object o = null;
        try {
            o = future.get();
            //o = future.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(future.isDone());
        System.out.println(o);
    }

    private static void testSumbitCallable(ExecutorService service) {
        Future<User> future = null;
        try {
            future = service.submit(new Callable<User>() {
                public User call() throws Exception {
                    User user = new User();
                    user.setUserID("007");
                    user.setUsername("James Bond");
                    user.setPassword("biubiubibu");
                    user.sayHello();
                    return user;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        User user = null;
        try {
            //get方法为阻塞方法
            user = future.get();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
