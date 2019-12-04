package com.wuyk.seckill.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品类
 * Created by WUYK on 2019-12-04.
 */
@Data
public class Seckill implements Serializable {
    /**
     * 商品id
     */
    private Long seckillId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 原价
     */
    private BigDecimal price;

    /**
     * 秒杀价
     */
    private BigDecimal costPrice;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 秒杀开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 剩余库存数
     */
    private Long stockCount;
}
