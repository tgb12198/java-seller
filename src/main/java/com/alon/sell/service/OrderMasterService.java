package com.alon.sell.service;

import com.alon.sell.dto.OrderInfo;
import org.hibernate.criterion.Order;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 19:17
 * @description：订单信息
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface OrderMasterService {
    //创建订单
    OrderInfo create(OrderInfo orderInfo);

    //查询单个订单
    OrderInfo findOne(String orderId);

    //查询订单列表
    Page<OrderInfo> findList(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderInfo cancel(OrderInfo orderInfo);

    //完结订单
    OrderInfo finished(OrderInfo orderInfo);

    //支付订单
    OrderInfo payOrder(OrderInfo orderInfo);
}
