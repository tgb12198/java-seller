package com.alon.sell.service.impl;

import com.alon.sell.dataobject.OrderMaster;
import com.alon.sell.dto.OrderInfo;
import com.alon.sell.enums.ResultEnum;
import com.alon.sell.exception.SellException;
import com.alon.sell.service.BuyerService;
import com.alon.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/3 22:30
 * @description：买家
 * @modified By：
 * @version: v1.0.0.1$
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderInfo findOrderOne(String openId, String orderId) {
        OrderInfo orderInfo = checkOrder(openId, orderId);
        return orderInfo;
    }

    @Override
    public void cancelOrder(String openId, String orderId) {
        OrderInfo orderInfo = checkOrder(openId, orderId);
        if (orderInfo == null) {
            log.error("【订单取消】,订单取消失败，订单不存在.");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMasterService.cancel(orderInfo);
        return;
    }

    private OrderInfo checkOrder(String openId, String orderId) {
        OrderInfo orderInfo = orderMasterService.findOne(orderId);
        if (!orderInfo.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("【查询订单】订单openid不一致，opneId:{},orderInfo:{}", openId, orderInfo);
            throw new SellException(ResultEnum.BUYER_OPNEID_ERROR);
        }
        return orderInfo;
    }
}
