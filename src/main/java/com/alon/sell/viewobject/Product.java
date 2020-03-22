package com.alon.sell.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 20:53
 * @description：商品
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
public class Product {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
