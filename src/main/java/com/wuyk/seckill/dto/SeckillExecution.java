package com.wuyk.seckill.dto;

import com.wuyk.seckill.entity.SeckillOrder;
import com.wuyk.seckill.enums.SeckillStatEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 封装执行秒杀后的结果
 */
@Data
@ToString
public class SeckillExecution {

    private Long seckillId;

    /**
     * 秒杀执行结果状态
     */
    private int state;

    /**
     * 状态表示
     */
    private String stateInfo;

    /**
     * 秒杀成功的订单对象
     */
    private SeckillOrder seckillOrder;

    public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.seckillOrder = seckillOrder;
    }

    public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }
}
