package com.wuyk.seckill.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTemplateConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisForString(){
//        redisTemplate.boundValueOps("1_redis_config_test").set("1_value_test");
        redisTemplate.boundValueOps("2_redis_config_test").set("2_value_test");
    }

    @Test
    public void testRedisForList(){
        List<String> list = new ArrayList<>();
        list.add(0, "测试");
        redisTemplate.boundHashOps("redis_for_list").put("list", list);
    }

}