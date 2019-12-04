package com.wuyk.seckill.service.impl;

import com.wuyk.seckill.dto.Exposer;
import com.wuyk.seckill.dto.SeckillExecution;
import com.wuyk.seckill.entity.Seckill;
import com.wuyk.seckill.entity.SeckillOrder;
import com.wuyk.seckill.enums.SeckillStatEnum;
import com.wuyk.seckill.exception.RepeatKillException;
import com.wuyk.seckill.exception.SeckillCloseException;
import com.wuyk.seckill.exception.SeckillException;
import com.wuyk.seckill.mapper.SeckillMapper;
import com.wuyk.seckill.mapper.SeckillOrderMapper;
import com.wuyk.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by WUYK on 2019-12-04.
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    //设置盐值字符串，随便定义，用于混淆MD5值
    private final String salt = "sjajaspu-i-2jrfm;sd";
    //设置秒杀redis缓存的key
    private final String key = "seckill";

    private SeckillMapper seckillMapper;
    private SeckillOrderMapper seckillOrderMapper;
    private RedisTemplate redisTemplate;

    @Autowired
    public SeckillServiceImpl(SeckillMapper seckillMapper,SeckillOrderMapper seckillOrderMapper, RedisTemplate redisTemplate) {
        this.seckillMapper = seckillMapper;
        this.seckillOrderMapper = seckillOrderMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Seckill> findAll() {
        List<Seckill> seckillList = redisTemplate.boundHashOps("seckill").values();
        if (seckillList == null || seckillList.size() == 0){
            //说明缓存中没有秒杀列表数据
            //查询数据库中秒杀列表数据，并将列表数据循环放入redis缓存中
            seckillList = seckillMapper.findAll();
            for (Seckill seckill : seckillList){
                //将秒杀列表数据依次放入redis缓存中，key:秒杀表的ID值；value:秒杀商品数据
                redisTemplate.boundHashOps(key).put(seckill.getSeckillId(), seckill);
                log.info("findAll -> 从数据库中读取放入缓存中");
            }
        }else{
            log.info("findAll -> 从缓存中读取");
        }
        return seckillList;
    }

    @Override
    public Seckill findById(long seckillId) {
        return seckillMapper.findById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
        if (seckill == null) {
            //说明redis缓存中没有此key对应的value
            //查询数据库，并将数据放入缓存中
            seckill = seckillMapper.findById(seckillId);
            if (seckill == null) {
                //说明没有查询到
                return new Exposer(false, seckillId);
            } else {
                //查询到了，存入redis缓存中。 key:秒杀表的ID值； value:秒杀表数据
                redisTemplate.boundHashOps(key).put(seckill.getSeckillId(), seckill);
                log.info("RedisTemplate -> 从数据库中读取并放入缓存中");
            }
        } else {
            log.info("RedisTemplate -> 从缓存中读取");
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //获取系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转换特定字符串的过程，不可逆的算法
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5)throws SeckillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：1.减库存；2.储存秒杀订单
        Date nowTime = new Date();

        try {
            //记录秒杀订单信息
            int insertCount = seckillOrderMapper.insertOrder(seckillId, money, userPhone);
            //唯一性：seckillId,userPhone，保证一个用户只能秒杀一件商品
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存
                int updateCount = seckillMapper.reduceStock(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新记录，秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒杀成功
                    SeckillOrder seckillOrder = seckillOrderMapper.findById(seckillId, userPhone);

                    //更新缓存（更新库存数量）
                    Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
                    seckill.setStockCount(seckill.getSeckillId() - 1);
                    redisTemplate.boundHashOps(key).put(seckillId, seckill);

                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, seckillOrder);
                }
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //所有编译期异常，转换为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
    
    //生成MD5值
    private String getMD5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
