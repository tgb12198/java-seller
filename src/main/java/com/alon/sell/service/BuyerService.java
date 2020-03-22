package com.alon.sell.service;

import com.alon.sell.dto.OrderInfo;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/3 22:28
 * @description：买家
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface BuyerService {

    OrderInfo findOrderOne(String openId, String orderId);

    void cancelOrder(String openId, String orderId);
}
