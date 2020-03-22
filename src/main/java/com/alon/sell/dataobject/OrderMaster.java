package com.alon.sell.dataobject;

import com.alon.sell.enums.OrderStatusEnum;
import com.alon.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 10:21
 * @description：订单
 * @modified By：
 * @version: v1.0.0.1$
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * 订单号
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.NOPAY.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
