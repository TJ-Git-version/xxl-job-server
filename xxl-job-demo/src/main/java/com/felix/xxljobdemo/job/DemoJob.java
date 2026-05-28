package com.felix.xxljobdemo.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class DemoJob {

    @XxlJob("testJob")
    public void testJob() {
        System.out.println("xxl-job, 执行成功");
    }


    @XxlJob("userJob")
    public void userJob() {
        System.out.println("user-job, 执行成功");
    }

}
