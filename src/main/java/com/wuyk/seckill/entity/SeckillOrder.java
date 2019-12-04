package com.wuyk.seckill.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀订单
 * Created by WUYK on 2019-12-04.
 */
@Data
public class SeckillOrder implements Serializable {

    /**
     * 秒杀商品id
     */
    private Long seckillId;

    /**
     * 支付金额
     */
    private BigDecimal money;

    /**
     * 用户手机号
     */
    private Long userPhone;

    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 订单状态， -1:无效 0:成功 1:已付款
     */
    private Boolean status;

    /**
     * 秒杀商品，和订单是一对多的关系
     */
    private Seckill seckill;
}
