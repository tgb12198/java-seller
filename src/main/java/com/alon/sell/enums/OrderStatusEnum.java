package com.alon.sell.enums;

import lombok.Getter;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 10:25
 * @description：订单状态
 * @modified By：
 * @version: v1.0.0.1$
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新建订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
