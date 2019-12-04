package com.wuyk.seckill.service;

import com.wuyk.seckill.dto.Exposer;
import com.wuyk.seckill.dto.SeckillExecution;
import com.wuyk.seckill.entity.Seckill;
import com.wuyk.seckill.exception.SeckillException;

import java.math.BigDecimal;
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

    /**
     * 获取某一条商品秒杀信息
     *
     * @param seckillId
     * @return
     */
    Seckill findById(long seckillId);

    /**
     * 秒杀开始时输出暴露秒杀的地址
     * 否者输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀的操作
     *
     * @param seckillId
     * @param userPhone
     * @param money
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5)
            throws SeckillException;
}
