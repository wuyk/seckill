package com.wuyk.seckill.mapper;

import com.wuyk.seckill.entity.Seckill;
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
public class SeckillMapperTest {

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void findAll() {
        List<Seckill> all = seckillMapper.findAll();
        for (Seckill seckill : all) {
            System.out.println(seckill.getTitle());
        }
    }
}