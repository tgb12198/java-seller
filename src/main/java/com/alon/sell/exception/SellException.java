package com.alon.sell.exception;

import com.alon.sell.enums.ResultEnum;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 19:54
 * @description：异常
 * @modified By：
 * @version: v1.0.0.1$
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
