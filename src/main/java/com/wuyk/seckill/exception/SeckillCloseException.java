package com.wuyk.seckill.exception;

/**
 * Created by WUYK on 2019-12-04.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
