package com.alon.sell.enums;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 12:58
 * @description：商品状态
 * @modified By：
 * @version: v1.0.0.1$
 */
public enum ProductStatusEnum {
    UP(0, "上架"),
    DOWN(1, "下架");

    public Integer getCode() {
        return code;
    }

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
