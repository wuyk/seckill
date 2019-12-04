package com.wuyk.seckill.exception;

import com.wuyk.seckill.exception.SeckillException;

/**
 * 重复秒杀异常
 * Created by WUYK on 2019-12-04.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
