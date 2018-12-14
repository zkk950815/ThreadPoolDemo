package com.zkk.test;

import java.util.ArrayList;
import java.util.List;

public class ThreadGroupDemo {
    public static void main(String[] args) {
        System.out.println("main线程开始");
        Runnable runnable;
        List<Thread> threadList = new ArrayList<>();
        ThreadGroup group = new ThreadGroup("group");
        //未设置成功
        group.setDaemon(true);
        for (int i = 0;i<10;i++) {
            //runnable = () ->System.out.println(Thread.currentThread().getThreadGroup().getName() + ": " + Thread.currentThread().getName());
            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getThreadGroup().getName() + ": " + Thread.currentThread().getName());
                }
            };


            threadList.add(new Thread(group, runnable));
        }


        threadList.forEach(thread -> System.out.println(thread.isDaemon()));
        System.out.println(group.isDaemon());
        System.out.println("main线程结束");
    }
}
