package com.alon.sell.enums;

import lombok.Getter;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 10:30
 * @description：支付状态
 * @modified By：
 * @version: v1.0.0.1$
 */
@Getter
public enum PayStatusEnum {
    NOPAY(0, "未支付"),
   // PAYING(1,"支付中"),
    PAY(1, "已支付");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
