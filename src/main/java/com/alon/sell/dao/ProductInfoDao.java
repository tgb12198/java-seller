package com.alon.sell.dao;

import com.alon.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/30 21:29
 * @description：商品
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findProductInfosByProductStatus(Integer productStatus);
}
