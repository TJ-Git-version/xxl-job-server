package com.felix.springboottask.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingTask {

    //六星表达式
    //秒 分 时 日 月 周
    //0 0 2 * * ? 表示每天的2点0分0秒执行
    //0 */5 * * * ? 表示每5分钟执行一次
    //0 0 * * * * 表示每小时执行一次
    // 0 0 1 1 * ? 每月1号执行

    @Scheduled(cron = "0/1 * * * * ?")
    //@Scheduled(fixedRate = 1000)
    public void testTask() {
        System.out.println(Thread.currentThread().getName() + "：任务执行了...");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    //@Scheduled(fixedRate = 1000)
    public void testTask2() {
        System.out.println(Thread.currentThread().getName() + "：任务执行了...");
    }

}
