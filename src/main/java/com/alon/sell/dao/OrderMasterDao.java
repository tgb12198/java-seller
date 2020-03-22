package com.alon.sell.dao;

import com.alon.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 10:50
 * @description：订单
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
