package com.alon.sell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alon.sell.dataobject.OrderDetail;
import com.alon.sell.dto.OrderInfo;
import com.alon.sell.enums.OrderStatusEnum;
import com.alon.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void create() {
        OrderInfo order = new OrderInfo();
        order.setBuyerName("Alon");
        order.setBuyerAddress("松鹤楼");
        order.setBuyerPhone("12345678912");
        order.setBuyerOpenid("110110");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("123456");
        od1.setProductQuantity(1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("456789");
        od2.setProductQuantity(1);

        OrderDetail od3 = new OrderDetail();
        od3.setProductId("789123");
        od3.setProductQuantity(2);

        orderDetailList.add(od1);
        orderDetailList.add(od2);
        orderDetailList.add(od3);

        order.setOrderDetailList(orderDetailList);
        OrderInfo orderInfo = orderMasterService.create(order);
        System.out.println(JSON.toJSONString(orderInfo));
    }

    @Test
    public void findOne() {
        OrderInfo orderInfo = orderMasterService.findOne("15806291802765878");
        System.out.println(JSON.toJSONString(orderInfo));
        log.info("result={}", orderInfo);
        Assert.assertEquals("15806291802765878", orderInfo.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0, 10);
        Page<OrderInfo> orderInfoPage = orderMasterService.findList("110110", request);
        log.info("【result={}】", orderInfoPage.getContent());
        System.out.println(JSON.toJSONString(orderInfoPage));
        Assert.assertNotEquals(0, orderInfoPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderInfo order = orderMasterService.findOne("15806291802765878");
        OrderInfo orderInfo = orderMasterService.cancel(order);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderInfo.getOrderStatus());
    }

    @Test
    public void finished() {
    }

    @Test
    public void payOrder() {
    }
}