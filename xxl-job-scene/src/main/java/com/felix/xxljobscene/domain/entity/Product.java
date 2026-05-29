package com.felix.xxljobscene.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String productNo;

    private String productName;

    private Integer categoryId;

    private Integer brandId;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private BigDecimal costPrice;

    private Integer stock;

    private Integer status;

    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}