package com.alon.sell.dao;

import com.alon.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 10:53
 * @description：订单详情
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrOrderId(String orderId);
}
