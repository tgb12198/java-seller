package com.alon.sell.dto;

import lombok.Data;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 21:07
 * @description：购物车
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
public class Cart {

    /**
     * 产品Id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public Cart(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
