package com.alon.sell.enums;

import lombok.Getter;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 20:05
 * @description：返回结果
 * @modified By：
 * @version: v1.0.0.1$
 */
@Getter
public enum ResultEnum {

    PARAMS_ERROR(00000, "参数错误"),
    JSON_CONVERT_ERROR(10000, "转换错误"),

    PRODUCT_NOT_EXIST(00001, "商品不存在"),
    PRODUCT_STOCK_INSUFFICIENT(00002, "商品库存不足"),
    ORDER_NOT_EXIST(00003, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(00004, "订单详情不存在"),
    ORDER_STATUS_ERROR(00005, "订单状态错误"),
    ORDER_DETAIL_EMPTY(00006, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(00007, "支付状态错误"),
    ORDER_UPDATE_FAIL(00010, "订单修改失败"),
    ORDER_CART_EMPTY(00011, "购物车为空"),
    BUYER_OPNEID_ERROR(00012, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(00013,"微信公众号网页授权错误"),
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
