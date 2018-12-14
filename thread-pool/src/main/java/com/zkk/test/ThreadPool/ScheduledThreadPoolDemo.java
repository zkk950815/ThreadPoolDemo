package com.zkk.test.ThreadPool;

import com.zkk.test.utils.DateUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(DateUtil.dateUtil(System.currentTimeMillis()));
            }
        };
        //testSchedule(service, runnable);
        testScheduleAtFixedRate(service, runnable);
    }

    private static void testScheduleAtFixedRate(ScheduledExecutorService service, Runnable runnable) {
        ScheduledFuture<?> future = service.scheduleAtFixedRate(runnable, 5, 5, TimeUnit.SECONDS);
    }

    private static void testSchedule(ScheduledExecutorService service, Runnable runnable) {
        try {
            service.schedule(runnable,5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
