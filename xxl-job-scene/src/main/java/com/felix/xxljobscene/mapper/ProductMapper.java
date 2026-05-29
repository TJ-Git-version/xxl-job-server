package com.felix.xxljobscene.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.xxljobscene.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectByLastId(@Param("lastId") Long lastId, @Param("pageSize") Integer pageSize);

    List<Product> selectByLastIdAndShard(@Param("lastId") Long lastId,
                                         @Param("pageSize") int pageSize,
                                         @Param("shardIndex") int shardIndex,
                                         @Param("shardTotal") int shardTotal);
}