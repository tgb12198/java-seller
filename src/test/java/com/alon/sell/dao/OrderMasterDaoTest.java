package com.alon.sell.dao;

import com.alibaba.fastjson.JSON;
import com.alon.sell.dataobject.OrderMaster;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void findByBuyerOpenId() {
        PageRequest request = PageRequest.of(0, 10);
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid("110110", request);
        System.out.println(JSON.toJSONString(orderMasterPage));
    }

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("Alon");
        orderMaster.setBuyerPhone("12345678900");
        orderMaster.setBuyerAddress("suzhou");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(12.5));

        OrderMaster order = orderMasterDao.save(orderMaster);

        System.out.println(JSON.toJSONString(order));
    }
}