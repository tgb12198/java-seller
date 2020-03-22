package com.alon.sell.dto;

import com.alon.sell.dataobject.OrderDetail;
import com.alon.sell.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 19:13
 * @description：订单信息
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo {
    /**
     * 订单号
     */
    private String OrderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认未支付
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetailList;
}
