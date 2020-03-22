package com.alon.sell.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/2 22:56
 * @description：订单表单实体
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址不能为空")
    private  String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "微信opneid不能为空")
    private String openid;

    /**
     * 所选物品
     */
    @NotEmpty(message = "物品不能为空")
    private String items;
}