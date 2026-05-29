package com.felix.xxljobscene.job;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felix.xxljobscene.domain.entity.Product;
import com.felix.xxljobscene.mapper.ProductMapper;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductJobHandler {

    private final ProductMapper productMapper;

    /**
     * 一次全量同步
     */
    @XxlJob(value = "syncProductDemo1")
    public void syncProductDemo1() {
        long startTime = System.currentTimeMillis();
        XxlJobHelper.log("开始执行同步任务");
        List<Product> products = productMapper.selectList(null);
        XxlJobHelper.log("products size: {}", products.size());
        XxlJobHelper.log("结束执行同步任务，耗时{}毫秒", System.currentTimeMillis() - startTime);
    }

    /**
     * 分页同步
     */
    @SneakyThrows
    @XxlJob(value = "syncProductDemo2")
    public void syncProductDemo2() {
        long startTime = System.currentTimeMillis();
        XxlJobHelper.log("开始执行同步任务");
        Long count = productMapper.selectCount(null);
        int pageSize = 2000;
        int totalPage = (int) Math.ceil((double) count / pageSize);
        XxlJobHelper.log("totalPage: {}", totalPage);
        for (int i = 0; i < totalPage; i++) {
            XxlJobHelper.log("currentPage: {}", i);
            Page<Product> page = new Page<>(i, pageSize);
            productMapper.selectPage(page, null);
            if (page.getRecords() == null || page.getRecords().isEmpty()) {
                break;
            }
            XxlJobHelper.log("page size: {}", page.getRecords().size());
            TimeUnit.MILLISECONDS.sleep(300);
        }
        XxlJobHelper.log("结束执行同步任务，耗时{}毫秒", System.currentTimeMillis() - startTime);
    }

    /**
     * ID游标同步
     */
    @SneakyThrows
    @XxlJob(value = "syncProductDemo3")
    public void syncProductDemo3() {
        long startTime = System.currentTimeMillis();
        XxlJobHelper.log("开始执行同步任务");
        Long lastId = 0L;
        Integer pageSize = 2000;
        while (true) {
            List<Product> products = productMapper.selectByLastId(lastId, pageSize);
            if (products == null || products.isEmpty()) {
                break;
            }
            XxlJobHelper.log("page size: {}", products.size());
            lastId = products.getLast().getId();
            // 模拟处理时间
            TimeUnit.MILLISECONDS.sleep(300);
        }
        XxlJobHelper.log("结束执行同步任务，耗时{}毫秒", System.currentTimeMillis() - startTime);
    }

    /**
     * 分片广播+ID游标
     */
    @SneakyThrows
    @XxlJob(value = "syncProductDemo4")
    public void syncProductDemo4() {
        long startTime = System.currentTimeMillis();
        XxlJobHelper.log("开始执行同步任务");
        // 获取分片索引
        int shardIndex = XxlJobHelper.getShardIndex();
        // 获取分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        XxlJobHelper.log("分片参数: shardIndex={}, shardTotal={}", shardIndex, shardTotal);
        Long lastId = 0L;
        while (true) {
            List<Product> products = productMapper.selectByLastIdAndShard(lastId, 2000, shardIndex, shardTotal);
            if (products == null || products.isEmpty()) {
                break;
            }
            lastId = products.getLast().getId();
            XxlJobHelper.log("page size: {}", products.size());
            XxlJobHelper.log("lastId: {}", lastId);
            // 模拟处理时间
            TimeUnit.MILLISECONDS.sleep(300);
        }
        XxlJobHelper.log("结束执行同步任务，耗时{}毫秒", System.currentTimeMillis() - startTime);
    }

}
