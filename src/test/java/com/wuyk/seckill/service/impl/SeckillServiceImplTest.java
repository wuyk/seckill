package com.wuyk.seckill.service.impl;

import com.wuyk.seckill.entity.Seckill;
import com.wuyk.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void findAll() {
        List<Seckill> all = seckillService.findAll();
        System.out.println("all:"+all);
    }
}