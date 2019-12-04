package com.wuyk.seckill.mapper;

import com.wuyk.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
@Mapper
public interface SeckillMapper {

    List<Seckill> findAll();
}
