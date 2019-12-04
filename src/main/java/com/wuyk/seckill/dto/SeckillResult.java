package com.wuyk.seckill.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 封装JSON返回的结果格式
 */
@Data
@ToString
public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
