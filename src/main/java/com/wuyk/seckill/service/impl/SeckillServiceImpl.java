package com.wuyk.seckill.service.impl;

import com.wuyk.seckill.entity.Seckill;
import com.wuyk.seckill.mapper.SeckillMapper;
import com.wuyk.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private SeckillMapper seckillMapper;

    @Autowired
    public SeckillServiceImpl(SeckillMapper seckillMapper) {
        this.seckillMapper = seckillMapper;
    }

    @Override
    public List<Seckill> findAll() {
        return null;
    }
}
