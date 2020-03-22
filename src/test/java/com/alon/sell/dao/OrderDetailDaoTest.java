package com.alon.sell.dao;

import com.alibaba.fastjson.JSON;
import com.alon.sell.dataobject.OrderDetail;
import org.apache.catalina.LifecycleState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void findByOrOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrOrderId("123456");
        System.out.println(JSON.toJSONString(orderDetailList));
    }

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123457");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(8.8));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("pdsrz.jpg");

        OrderDetail detail = orderDetailDao.save(orderDetail);
        System.out.println(JSON.toJSONString(detail));
    }
}