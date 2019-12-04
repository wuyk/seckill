package com.wuyk.seckill.service;

import com.wuyk.seckill.entity.Seckill;

import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
public interface SeckillService {

    /**
     * 获取所有的秒杀商品列表
     *
     * @return
     */
    List<Seckill> findAll();
}
