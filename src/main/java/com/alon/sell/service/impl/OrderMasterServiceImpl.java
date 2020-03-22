package com.alon.sell.service.impl;

import com.alon.sell.converter.ConvertUtils;
import com.alon.sell.dao.OrderDetailDao;
import com.alon.sell.dao.OrderMasterDao;
import com.alon.sell.dataobject.OrderDetail;
import com.alon.sell.dataobject.OrderMaster;
import com.alon.sell.dataobject.ProductInfo;
import com.alon.sell.dto.Cart;
import com.alon.sell.dto.OrderInfo;
import com.alon.sell.enums.OrderStatusEnum;
import com.alon.sell.enums.PayStatusEnum;
import com.alon.sell.enums.ResultEnum;
import com.alon.sell.exception.SellException;
import com.alon.sell.service.OrderMasterService;
import com.alon.sell.service.ProductService;
import com.alon.sell.utils.GenerateKey;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 19:26
 * @description：订单信息
 * @modified By：
 * @version: v1.0.0.1$
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderInfo create(OrderInfo orderInfo) {
        //生成订单号
        String orderId = GenerateKey.generateOrderKey();
        //1、查询商品（数量、价格）
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail item : orderInfo.getOrderDetailList()) {
            ProductInfo productInfo = productService.findById(item.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算价格
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(item.getProductQuantity())).add(orderAmount);

            //3、写入数据库（orderMaster和orderDetail）订单详情入库
            item.setDetailId(GenerateKey.generateOrderDetailId());
            item.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, item);
            orderDetailDao.save(item);
        }
        //3、写入数据库（orderMaster和orderDetail）订单主表
        OrderMaster order = new OrderMaster();
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderInfo, order);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.NOPAY.getCode());
        orderMasterDao.save(order);
        //4、扣库存
        List<Cart> cartList = orderInfo.getOrderDetailList().stream().map(e -> new Cart(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartList);
        return orderInfo;
    }

    @Override
    public OrderInfo findOne(String orderId) {
        OrderInfo orderInfo = new OrderInfo();
        OrderMaster order = orderMasterDao.findById(orderId).get();
        if (order == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(order, orderInfo);
        orderInfo.setOrderDetailList(orderDetailList);
        return orderInfo;
    }

    @Override
    public Page<OrderInfo> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderInfo> orderInfoList = ConvertUtils.convert2List(orderMasterPage.getContent());
        Page<OrderInfo> orderInfoPage = new PageImpl<OrderInfo>(orderInfoList, pageable, orderMasterPage.getTotalElements());
        return orderInfoPage;
    }

    @Override
    @Transactional
    public OrderInfo cancel(OrderInfo orderInfo) {
        OrderMaster order = new OrderMaster();
        //1、判断订单状态
        if (!orderInfo.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderInfo.getOrderId(), orderInfo.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2、修改订单状态
        orderInfo.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderInfo, order);
        OrderMaster orderMaster = orderMasterDao.save(order);
        if (orderMaster == null) {
            log.error("【取消订单】取消订单失败，orderId={},orderStatus={}", order.getOrderId(), order.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3、返还库存
        List<Cart> cartList = orderInfo.getOrderDetailList().stream()
                .map(e -> new Cart(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartList);
        //4、已支付，退款
        //TODO
        return orderInfo;
    }

    @Override
    public OrderInfo finished(OrderInfo orderInfo) {
        //1、判断订单状态
        if (!orderInfo.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单完结】订单完结失败，orderId={},orderStatus={}", orderInfo.getOrderId(), orderInfo.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2、修改订单状态
        orderInfo.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderInfo, orderMaster);
        OrderMaster order = orderMasterDao.save(orderMaster);
        if (order == null) {
            log.error("【订单完结】订单不存在，orderMaster:{}", orderMaster);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderInfo;
    }

    @Override
    public OrderInfo payOrder(OrderInfo orderInfo) {
        //1、判断订单状态
        if (!orderInfo.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付】订单状态错误，orderId={},orderStatus={}", orderInfo.getOrderId(), orderInfo.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2、判断订单支付状态
        if (!orderInfo.getPayStatus().equals(PayStatusEnum.NOPAY.getCode())) {
            log.error("【订单支付】支付失败，orderInfo:{}", orderInfo);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3、修改支付状态
        orderInfo.setPayStatus(PayStatusEnum.PAY.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderInfo, orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if (result == null) {
            log.error("【订单支付】支付失败，orderMaster:{}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderInfo;
    }
}
