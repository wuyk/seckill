package com.wuyk.seckill.exception;

/**
 * 秒杀异常
 * Created by WUYK on 2019-12-04.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
