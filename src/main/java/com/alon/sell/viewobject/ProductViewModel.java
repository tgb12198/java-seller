package com.alon.sell.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 19:36
 * @description：商品实体
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
public class ProductViewModel {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<Product> productInfoList;
}
