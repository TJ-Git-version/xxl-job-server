package com.felix.springboottask;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootTaskApplicationTests {

    @Test
    void contextLoads() {
        Timer timer = new Timer("MyTimer");
        // 3秒后开始执行，每1秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：任务执行了...");
                int i = 1 / 0; // 故意制造一个异常
            }
        }, 3000, 1000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTimer() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + "：任务执行了...");
        }, 1, 3, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
