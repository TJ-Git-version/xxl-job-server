package com.felix.xxljobdemo.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SyncJob {

    @XxlJob("syncJob")
    public void syncJob() {
        try {
            XxlJobHelper.log("开始执行同步任务");

            String jobParam = XxlJobHelper.getJobParam();
            int shardIndex = XxlJobHelper.getShardIndex();
            int shardTotal = XxlJobHelper.getShardTotal();
            XxlJobHelper.log("分片参数: shardIndex={}, shardTotal={}", shardIndex, shardTotal);

            XxlJobHelper.log("参数:{}", jobParam);
            XxlJobHelper.log("结束执行同步任务");
            // 手动标识任务成功
            XxlJobHelper.handleSuccess("执行同步任务成功");
        } catch (Exception e) {
            XxlJobHelper.log("异常：{}", e.getMessage());
            XxlJobHelper.handleFail("执行同步任务失败");
        }
    }

}
